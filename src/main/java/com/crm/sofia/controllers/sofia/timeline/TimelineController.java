package com.crm.sofia.controllers.sofia.timeline;


import com.crm.sofia.services.sofia.timeline.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/timeline-designer")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping(path = "/data")
    Object getData(@RequestParam("id") String id, @RequestParam Map<String, String> parameters) {
        return timelineService.getData(id, parameters);
    }

}
