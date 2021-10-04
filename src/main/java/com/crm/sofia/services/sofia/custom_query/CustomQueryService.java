package com.crm.sofia.services.sofia.custom_query;

import com.crm.sofia.dto.sofia.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.sofia.custom_query.CustomQueryMapper;
import com.crm.sofia.model.sofia.custom_query.CustomQuery;
import com.crm.sofia.repository.sofia.custom_query.CustomQueryRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CustomQueryService {
    @Autowired
    private CustomQueryMapper customQueryMapper;
    @Autowired
    private CustomQueryRepository customQueryRepository;
    @Autowired
    private JWTService jwtService;


    public List<CustomQueryDTO> getObject() {
        List<CustomQuery> entites = customQueryRepository.findAll();
        return customQueryMapper.map(entites);
    }

    public CustomQueryDTO getObject(Long id) {
        Optional<CustomQuery> optionalEntity = customQueryRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        CustomQuery entity = optionalEntity.get();
        CustomQueryDTO dto = customQueryMapper.map(entity);
        return dto;
    }
    public CustomQueryDTO postObject(CustomQueryDTO customQueryDto) {

        CustomQuery customQuery = customQueryMapper.map(customQueryDto);
        if (customQueryDto.getId() == null) {
            customQuery.setCreatedOn(Instant.now());
            customQuery.setCreatedBy(jwtService.getUserId());
        }
        customQuery.setModifiedOn(Instant.now());
        customQuery.setModifiedBy(jwtService.getUserId());
        CustomQuery savedData = customQueryRepository.save(customQuery);

        return customQueryMapper.map(savedData);
    }

    public void deleteObject(Long id) {
        Optional<CustomQuery> optionalEntity = customQueryRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        customQueryRepository.deleteById(optionalEntity.get().getId());
    }



}
