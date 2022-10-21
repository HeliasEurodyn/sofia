package com.crm.sofia.controllers.timeline;


import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.dto.sofia.timeline.TimelineResponseDTO;
import com.crm.sofia.services.timeline.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/timeline")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping(path = "/data")
    TimelineResponseDTO getData(@RequestParam("id") String id,
                                @RequestParam Map<String, String> parameters,
                                @RequestParam int currentPage) {
        return timelineService.getData(id, parameters,currentPage);
    }

    @PostMapping(path = "/data")
    String postData(@RequestParam("id") String id,
                    @RequestParam Map<String, String> parameters,
                    @RequestParam int currentPage) {
        Object response = timelineService.postData(id, parameters,currentPage);
        return "{\"response\": \"" + response.toString() + "\"}";
    }

    @GetMapping(path = "/by-id")
    TimelineDTO getObject(@RequestParam("id") String id) {
        return timelineService.getObjectIgnoringQuery(id);
    }

}
