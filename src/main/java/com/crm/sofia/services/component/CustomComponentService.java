package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.mapper.component.CustomComponentMapper;
import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.repository.component.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomComponentService {
    private final CustomComponentFieldService customComponentFieldService;
    private final ComponentRepository componentRepository;
    private final CustomComponentMapper componentMapper;
    private final EntityManager entityManager;

    public CustomComponentService(ComponentRepository componentRepository,
                                  CustomComponentMapper componentMapper,
                                  CustomComponentFieldService customComponentFieldService,
                                  EntityManager entityManager) {
        this.componentRepository = componentRepository;
        this.customComponentFieldService = customComponentFieldService;
        this.componentMapper = componentMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public CustomComponentDTO postObject(CustomComponentDTO componentDTO) {
        CustomComponent component = this.componentMapper.mapDTO(componentDTO);

        CustomComponent customComponent = this.componentRepository.save(component);
        return this.componentMapper.map(customComponent);
    }

    @Transactional
    public CustomComponentDTO putObject(CustomComponentDTO componentDTO) {

        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(componentDTO.getId());
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        CustomComponent customComponent = optionalComponent.get();

        componentMapper.setDtoToEntity(componentDTO, customComponent);
        CustomComponent createdCustomComponent = this.componentRepository.save(customComponent);
        CustomComponentDTO createdCustomComponentDTO = this.componentMapper.map(createdCustomComponent);

        return createdCustomComponentDTO;
    }

    @Transactional
    public List<CustomComponentFieldDTO> putNewObjectFields(CustomComponentDTO componentDTO) {
        List<CustomComponentFieldDTO> createdCustomComponentFieldDTOs = new ArrayList<>();
        for (CustomComponentFieldDTO customComponentFieldDTO : componentDTO.getCustomComponentFieldList()) {
            CustomComponentFieldDTO createdCustomComponentFieldDTO = this.customComponentFieldService.saveCustomComponentField(customComponentFieldDTO, componentDTO.getId());
            createdCustomComponentFieldDTOs.add(createdCustomComponentFieldDTO);
        }

        List<Long> ids = createdCustomComponentFieldDTOs.stream().map(CustomComponentFieldDTO::getId).collect(Collectors.toList());
        this.customComponentFieldService.deleteObjectsNotInListForCustomComponent(ids, componentDTO.getId());

        return createdCustomComponentFieldDTOs;
    }


    public List<CustomComponentDTO> getObject() {
        List<CustomComponent> customComponents = this.componentRepository.findAll();
        return this.componentMapper.map(customComponents);
    }

    public void deleteObject(Long id) {
        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        this.componentRepository.deleteById(optionalComponent.get().getId());
    }

    @Transactional
    public void createDatabaseTable(CustomComponentDTO customComponentDTO) {


        int fieldCounter = 0;
        String sql = "";
        sql += "CREATE TABLE " + customComponentDTO.getName().replace(" ","");
        sql += " ( ";
        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {
            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += customComponentFieldDTO.getName().replace(" ","") +  " ";
            sql += " " + customComponentFieldDTO.getType().replace(" ","") ;
            if (customComponentFieldDTO.getType().equals("VARCHAR")) {
                sql += " ("+ customComponentFieldDTO.getSize().toString().replace(" ","") +") ";
            }
            sql += "\n";

            fieldCounter++;
        }
        sql += " ); ";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
//        Query query = entityManager.createNativeQuery(sql);
//        fieldCounter = 0;
//        query.setParameter("name", customComponentDTO.getName());
//        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {
//            query.setParameter("fieldname" + fieldCounter, customComponentFieldDTO.getName());
//            query.setParameter("fieldtype" + fieldCounter, customComponentFieldDTO.getType());
//            if (customComponentFieldDTO.getType().equals("VARCHAR")) {
//                query.setParameter("fieldsize" + fieldCounter, customComponentFieldDTO.getSize());
//            }
//            fieldCounter++;
//        }
//        query.executeUpdate();



//        int fieldCounter = 0;
//        String sql = "";
//        sql += "CREATE TABLE :name ";
//        sql += " ( ";
//        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {
//            if (fieldCounter > 0) {
//                sql += ",";
//            }
//            sql += " :fieldname" + fieldCounter + " ";
//            sql += " :fieldtype" + fieldCounter;
//            if (customComponentFieldDTO.getType().equals("VARCHAR")) {
//                sql += " (:fieldsize" + fieldCounter + ") ";
//            }
//            fieldCounter++;
//        }
//        sql += " ); ";
//
//
//        Query query = entityManager.createNativeQuery(sql);
//        fieldCounter = 0;
//        query.setParameter("name", customComponentDTO.getName());
//        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {
//            query.setParameter("fieldname" + fieldCounter, customComponentFieldDTO.getName());
//            query.setParameter("fieldtype" + fieldCounter, customComponentFieldDTO.getType());
//            if (customComponentFieldDTO.getType().equals("VARCHAR")) {
//                query.setParameter("fieldsize" + fieldCounter, customComponentFieldDTO.getSize());
//            }
//            fieldCounter++;
//        }
//        query.executeUpdate();

    }
}
