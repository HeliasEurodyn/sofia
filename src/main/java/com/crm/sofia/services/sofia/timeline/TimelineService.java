package com.crm.sofia.services.sofia.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.mapper.sofia.timeline.TimelineMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import com.crm.sofia.repository.sofia.timeline.TimelineRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.hibernate.HibernateException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Map;

@Service
public class TimelineService {

    private final TimelineMapper timelineMapper;
    private final TimelineRepository timelineRepository;
    private final JWTService jwtService;

    private final EntityManager entityManager;

    public TimelineService(TimelineMapper timelineMapper, TimelineRepository timelineRepository, JWTService jwtService, EntityManager entityManager) {
        this.timelineMapper = timelineMapper;
        this.timelineRepository = timelineRepository;
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    public Object getData(String id, Map<String, String> parameters) {

        Query query =buildQuery(id,parameters);
        return query.getResultList();
    }

    @Transactional
    @Modifying
    public Object postData(String id, Map<String, String> parameters) {
        try {
            Object lastInsertId;
            Query query =buildQuery(id,parameters);
            query.executeUpdate();
            lastInsertId = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            return lastInsertId;
        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public TimelineDTO getObject(String id) {
        Timeline model = timelineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist"));
        return timelineMapper.map(model);
    }

    private Query buildQuery(String id, Map<String, String> parameters){

        TimelineDTO dto = this.getObject(id);
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

        return query;

    }


}
