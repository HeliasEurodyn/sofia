package com.crm.sofia.services.sofia.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.mapper.sofia.timeline.TimelineMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import com.crm.sofia.repository.sofia.timeline.TimelineRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Map;

@Service
public class TimelineService {

    @Autowired
    private TimelineMapper timelineMapper;
    @Autowired
    private TimelineRepository timelineRepository;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private EntityManager entityManager;

    public Object getData(String id, Map<String, String> parameters) {
        TimelineDTO dto = this.getObject(id);
        String queryString = dto.getQuery();

        Query query = entityManager.createNativeQuery(queryString);

        if(queryString.contains(":userid")){
            query.setParameter("userid",this.jwtService.getUserId());
        }

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if(queryString.contains(":"+entry.getKey())){
                query.setParameter(entry.getKey(),entry.getValue());
            }
        }

        return query.getResultList();
    }

    public TimelineDTO getObject(String id) {
        Timeline model = timelineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist"));;
        return timelineMapper.map(model);
    }

}
