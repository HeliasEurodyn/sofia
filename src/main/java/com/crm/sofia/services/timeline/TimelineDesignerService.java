package com.crm.sofia.services.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.mapper.sofia.timeline.TimelineMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import com.crm.sofia.repository.sofia.timeline.TimelineRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TimelineDesignerService {


    @Autowired
    private TimelineMapper timelineMapper;
    @Autowired
    private TimelineRepository timelineRepository;
    @Autowired
    private JWTService jwtService;


    public List<TimelineDTO> getObject() {
        List<Timeline> entities = timelineRepository.findAll();
        return timelineMapper.mapEntitiesForList(entities);
    }

    public TimelineDTO getObject(String id) {
        Optional<Timeline> optionalEntity = timelineRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Timeline entity = optionalEntity.get();
        TimelineDTO dto = timelineMapper.map(entity);

        String encodedQuery = Base64.getEncoder().encodeToString(dto.getQuery().getBytes(StandardCharsets.UTF_8));
        dto.setQuery(encodedQuery);

        return dto;
    }


    public TimelineDTO postObject(TimelineDTO timelineDTO) {

        byte[] decodedQuery = Base64.getDecoder().decode(timelineDTO.getQuery());
        timelineDTO.setQuery(new String(decodedQuery));

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
