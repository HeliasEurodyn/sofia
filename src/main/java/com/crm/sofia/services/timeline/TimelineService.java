package com.crm.sofia.services.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.dto.sofia.timeline.TimelineResponseDTO;
import com.crm.sofia.mapper.sofia.timeline.TimelineMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import com.crm.sofia.repository.sofia.timeline.TimelineRepository;
import com.crm.sofia.services.auth.JWTService;
import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public TimelineResponseDTO getData(String id, Map<String, String> parameters , int currentPage) {
        TimelineDTO timelineDTO = this.getObject(id);
        TimelineResponseDTO timelineResponseDTO =new TimelineResponseDTO();
        Query query =buildQuery(timelineDTO,parameters,currentPage);
        try{
            timelineResponseDTO.setResultList(query.getResultList());

            timelineResponseDTO.setIsTheLastPage(true);
            if(timelineDTO.isHasPagination() && timelineDTO.getPageSize()!=null){
                checkLastPage(timelineDTO,timelineResponseDTO);
            }
            
            return  timelineResponseDTO;
        }catch (QueryException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }

    @Transactional
    @Modifying
    public Object postData(String id, Map<String, String> parameters,int currentPage) {
        try {
            Object lastInsertId;
            TimelineDTO timelineDTO = this.getObject(id);
            Query query =buildQuery(timelineDTO,parameters,currentPage);
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

    public TimelineDTO getObjectIgnoringQuery(String id) {
        Timeline model = timelineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist"));
        return timelineMapper.mapEntityIgnoringQuery(model);
    }

    private Query buildQuery(TimelineDTO dto, Map<String, String> parameters,int currentPage){

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

            if(dto.isHasPagination() && dto.getPageSize()!=null){
                nativeQuery.setFirstResult((currentPage-1)*dto.getPageSize());
                nativeQuery.setMaxResults(dto.getPageSize()+1);
            }

            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            return nativeQuery;
    }

    private void checkLastPage(TimelineDTO timelineDTO,TimelineResponseDTO timelineResponseDTO){

        List tempResultList = Optional.ofNullable(timelineResponseDTO.getResultList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Result List is Empty"));

        if(tempResultList.size()== timelineDTO.getPageSize()+1){
            timelineResponseDTO.setIsTheLastPage(false);
            int indexOfLastElement = tempResultList.size() - 1;
            tempResultList.remove(indexOfLastElement);
            timelineResponseDTO.setResultList(tempResultList);
        }else {
            timelineResponseDTO.setIsTheLastPage(true);
        }
    }


}
