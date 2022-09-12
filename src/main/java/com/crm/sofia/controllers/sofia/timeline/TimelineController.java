package com.crm.sofia.controllers.sofia.timeline;


import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.services.sofia.timeline.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/timeline")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping
    List<TimelineDTO> getObject() {
        return timelineService.getObject();
    }

    @GetMapping(path = "/by-id")
    TimelineDTO getObject(@RequestParam("id") String id) {
        return timelineService.getObject(id);
    }

    @PostMapping
    public TimelineDTO postObject(@RequestBody TimelineDTO timelineDTO) throws IOException {
        return timelineService.postObject(timelineDTO);
    }

    @PutMapping
    public TimelineDTO putObject(@RequestBody TimelineDTO timelineDTO) {
        return timelineService.postObject(timelineDTO);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        timelineService.deleteObject(id);
    }

}
