package com.crm.sofia.services.rest_documentation;

import com.crm.sofia.dto.rest_documentation.RestDocumentationDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rest_documantation.RestDocumentationMapper;
import com.crm.sofia.model.rest_documentation.RestDocumentation;
import com.crm.sofia.repository.rest_documantation.RestDocumentationRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

        RestDocumentationDTO dto = restDocumentationMapper.map(entity);

        return dto;

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
