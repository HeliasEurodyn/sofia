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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TimelineService {


    @Autowired
    private TimelineMapper timelineMapper;
    @Autowired
    private TimelineRepository timelineRepository;
    @Autowired
    private JWTService jwtService;


    public List<TimelineDTO> getObject() {
        List<Timeline> entities = timelineRepository.findAll();
        return timelineMapper.map(entities);
    }

    public TimelineDTO getObject(String id) {
        Optional<Timeline> optionalEntity = timelineRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Timeline entity = optionalEntity.get();
        TimelineDTO dto = timelineMapper.map(entity);
        return dto;
    }


    public TimelineDTO postObject(TimelineDTO timelineDTO) {

        Timeline timeline = timelineMapper.map(timelineDTO);
        if (timeline.getId() == null) {
            timeline.setCreatedOn(Instant.now());
            timeline.setCreatedBy(jwtService.getUserId());
        }
        timeline.setModifiedOn(Instant.now());
        timeline.setModifiedBy(jwtService.getUserId());
        Timeline savedData = timelineRepository.save(timeline);

        return timelineMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<Timeline> optionalEntity = timelineRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        timelineRepository.deleteById(optionalEntity.get().getId());
    }



}
