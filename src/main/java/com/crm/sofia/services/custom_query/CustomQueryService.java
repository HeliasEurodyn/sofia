package com.crm.sofia.services.custom_query;

import com.crm.sofia.dto.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.custom_query.CustomQueryMapper;
import com.crm.sofia.model.custom_query.CustomQuery;
import com.crm.sofia.repository.custom_query.CustomQueryRepository;
import com.crm.sofia.services.auth.JWTService;
import org.hibernate.HibernateException;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomQueryService {

    private final JWTService jwtService;
    private final EntityManager entityManager;
    @Autowired
    private CustomQueryMapper customQueryMapper;
    @Autowired
    private CustomQueryRepository customQueryRepository;

    public CustomQueryService(JWTService jwtService,
                              EntityManager entityManager) {
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    public List<CustomQueryDTO> getObject() {
        List<CustomQuery> entites = customQueryRepository.findAll();
        return customQueryMapper.map(entites);
    }

    public CustomQueryDTO getObject(String id) {
        Optional<CustomQuery> optionalEntity = customQueryRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        CustomQuery entity = optionalEntity.get();
        CustomQueryDTO dto = customQueryMapper.map(entity);
        return dto;
    }

    public Object getDataObjects(String id, Map<String, String> parameters) {
        CustomQueryDTO dto = this.getObject(id);
        String queryString = dto.getQuery();
        Query query = entityManager.createNativeQuery(queryString);

        if( queryString.contains(":userid")){
            query.setParameter("userid",this.jwtService.getUserId());
        }

        parameters
                .entrySet()
                .stream()
                .filter(entry->  queryString.contains(":"+entry.getKey()))
                .forEach(entry ->query.setParameter(entry.getKey(),entry.getValue()));

        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return nativeQuery.getResultList();
    }

    public Object getData(String id, Map<String, String> parameters) {
        CustomQueryDTO dto = this.getObject(id);
        String queryString = dto.getQuery();
        queryString = queryString.replace("##userid##", this.jwtService.getUserId());
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            queryString = queryString.replace("##" + entry.getKey() + "##", entry.getValue());
        }
        Query query = entityManager.createNativeQuery(queryString);
        return query.getResultList();
    }

    @Transactional
    @Modifying
    public Object postData(String id, Map<String, String> parameters) {
        try {
            Object lastInsertId;
            CustomQueryDTO dto = this.getObject(id);
            String queryString = dto.getQuery();
            queryString = queryString.replace("##userid##", this.jwtService.getUserId());
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                queryString = queryString.replace("##" + entry.getKey() + "##", entry.getValue());
            }
            Query query = entityManager.createNativeQuery(queryString);

            query.executeUpdate();
            lastInsertId = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();

            return lastInsertId;
        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
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

    public void deleteObject(String id) {
        Optional<CustomQuery> optionalEntity = customQueryRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        customQueryRepository.deleteById(optionalEntity.get().getId());
    }

}
