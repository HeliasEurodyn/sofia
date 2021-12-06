package com.crm.sofia.services.sofia.custom_query;

import com.crm.sofia.dto.sofia.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.sofia.custom_query.CustomQueryMapper;
import com.crm.sofia.model.sofia.custom_query.CustomQuery;
import com.crm.sofia.repository.sofia.custom_query.CustomQueryRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomQueryService {

    @Autowired
    private CustomQueryMapper customQueryMapper;

    @Autowired
    private  CustomQueryRepository customQueryRepository;

    private final JWTService jwtService;
    private final EntityManager entityManager;

    public CustomQueryService(JWTService jwtService,
                              EntityManager entityManager) {
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

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

    public Object getData(Long id, Map<String, String> parameters) {
        CustomQueryDTO dto = this.getObject(id);
        String queryString = dto.getQuery();
        queryString = queryString.replace("##userid##", this.jwtService.getUserId().toString());
        for (Map.Entry<String,String> entry : parameters.entrySet()){
            queryString = queryString.replace("##"+entry.getKey()+"##", entry.getValue().toString());
        }
        Query query = entityManager.createNativeQuery(queryString);
        return query.getResultList();
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
