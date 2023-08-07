package com.crm.sofia.services.rest_documentation;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.rest_documentation.RestDocumentationDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rest_documantation.RestDocumentationMapper;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.crm.sofia.model.list.ListComponentField;
import com.crm.sofia.model.rest_documentation.RestDocumentation;
import com.crm.sofia.repository.rest_documantation.RestDocumentationRepository;
import com.crm.sofia.services.auth.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestDocumentationService {
    @Autowired
    RestDocumentationRepository restDocumentationRepository;

    @Autowired
    RestDocumentationMapper restDocumentationMapper;

    @Autowired
    JWTService jwtService;

    public RestDocumentationService() {
    }

    public List<RestDocumentationDTO> getObject() {
        List<RestDocumentation> entities = restDocumentationRepository.findAll();
        return restDocumentationMapper.map(entities);
    }

    public RestDocumentationDTO getObject(String id) {
        RestDocumentation entity = restDocumentationRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("RestDocumentation Does Not Exist"));

        entity.getRestDocumentationEndpoints()
                .stream()
                .filter(endpoint -> endpoint.getType().equals("list"))
                .forEach(endpoint -> {
                    List<ListComponentField> listComponentFilterFieldList =
                            endpoint.getList().getListComponentFilterFieldList()
                                    .stream()
                                    .filter(field -> field.getEditable() == true).collect(Collectors.toList());
                    endpoint.getList().setListComponentFilterFieldList(listComponentFilterFieldList);
        });

        entity.getRestDocumentationEndpoints()
                .stream()
                .filter(endpoint -> endpoint.getType().equals("form"))
                .filter(endpoint -> Arrays.asList("post", "put").contains(endpoint.getMethod()))
                .forEach(endpoint -> {

                             Map<String,Object> bodyJson = new LinkedHashMap<>();
                            endpoint.getForm().getComponent().getComponentPersistEntityList()
                                    .stream()
                                    .forEach(cpe -> {
                                        Map<String,Object> endpointJson = new LinkedHashMap<>();
                                        cpe.getComponentPersistEntityFieldList()
                                                .stream()
                                                .sorted(Comparator.comparingLong(cpef -> cpef.getShortOrder()==null?Long.MAX_VALUE:cpef.getShortOrder()))
                                                .forEach(cpef -> {

                                                    String fieldValue = cpef.getPersistEntityField().getType();

                                                    if(cpef.getPersistEntityField().getType().equals("datetime")){
                                                        fieldValue =  DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now());
                                                    }

                                                    if(cpef.getPersistEntityField().getPrimaryKey()){
                                                        fieldValue = null;
                                                    }

                                                    endpointJson.put(cpef.getPersistEntityField().getName(),fieldValue);
                                        });
                                        this.createChildrenJsonParts(endpointJson, cpe);
                                        bodyJson.put(cpe.getCode(),endpointJson);
                                    });
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonString = objectMapper.writeValueAsString(bodyJson);
                        String encodedJsonString =  Base64.getEncoder().encodeToString(jsonString.getBytes());
                        endpoint.setJsonString(encodedJsonString);
                    } catch (JsonProcessingException e) {

                    }
                });

        RestDocumentationDTO dto = restDocumentationMapper.map(entity);

        return dto;
    }

    public void createChildrenJsonParts(Map<String,Object> parrentJson, ComponentPersistEntity parrentCpe ) {
        Map<String,Object> bodyJson = new LinkedHashMap<>();
        parrentCpe.getComponentPersistEntityList()
                            .stream()
                            .forEach(cpe -> {
                                Map<String,Object> endpointJson = new LinkedHashMap<>();
                                cpe.getComponentPersistEntityFieldList()
                                        .stream()
                                        .sorted(Comparator.comparingLong(cpef -> cpef.getShortOrder()==null?Long.MAX_VALUE:cpef.getShortOrder()))
                                        .forEach(cpef -> {
                                            String fieldValue = cpef.getPersistEntityField().getType();
                                            if(cpef.getPersistEntityField().getType().equals("datetime")){
                                               // fieldValue = Instant.now().toString();
                                                fieldValue =  DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now());
                                            }
                                    endpointJson.put(cpef.getPersistEntityField().getName(),fieldValue);
                                });
                                this.createChildrenJsonParts(endpointJson, cpe);
                                if(endpointJson.size() > 0) {
                                    if((cpe.getMultiDataLine()==null)?false:cpe.getMultiDataLine()){
                                        Map<String,Object> mulitineEndpointJson = new LinkedHashMap<>();
                                        mulitineEndpointJson.put("0", endpointJson);
                                        bodyJson.put(cpe.getCode(), mulitineEndpointJson);
                                    }else {
                                        bodyJson.put(cpe.getCode(), endpointJson);
                                    }
                                }
                            });
       if(bodyJson.size() > 0) {
           parrentJson.put("sub-entities", bodyJson);
       }

    }

    public RestDocumentationDTO postObject(RestDocumentationDTO restDocumentationDTO) {

        RestDocumentation restDocumentation = restDocumentationMapper.map(restDocumentationDTO);
        if (restDocumentation.getId() == null) {
            restDocumentation.setCreatedOn(Instant.now());
            restDocumentation.setCreatedBy(jwtService.getUserId());
        }
        restDocumentation.setModifiedOn(Instant.now());
        restDocumentation.setModifiedBy(jwtService.getUserId());

        RestDocumentation savedData = restDocumentationRepository.save(restDocumentation);

        return restDocumentationMapper.map(savedData);
    }

    public void deleteObject(String id) {
        RestDocumentation optionalEntity = restDocumentationRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("RestDocumentation Does Not Exist"));

        restDocumentationRepository.deleteById(optionalEntity.getId());
    }
}
