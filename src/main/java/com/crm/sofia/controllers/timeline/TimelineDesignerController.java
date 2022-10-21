package com.crm.sofia.controllers.timeline;


import com.crm.sofia.dto.timeline.TimelineDTO;
import com.crm.sofia.services.timeline.TimelineDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/timeline-designer")
public class TimelineDesignerController {

    @Autowired
    private TimelineDesignerService timelineDesignerService;

    @GetMapping
    List<TimelineDTO> getObject() {
        return timelineDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    TimelineDTO getObject(@RequestParam("id") String id) {
        return timelineDesignerService.getObject(id);
    }

    @PostMapping
    public TimelineDTO postObject(@RequestBody TimelineDTO timelineDTO) throws IOException {
        return timelineDesignerService.postObject(timelineDTO);
    }

    @PutMapping
    public TimelineDTO putObject(@RequestBody TimelineDTO timelineDTO) {
        return timelineDesignerService.postObject(timelineDTO);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        timelineDesignerService.deleteObject(id);
    }

}
