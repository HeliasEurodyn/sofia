package com.crm.sofia.controllers.custom_query;

import com.crm.sofia.services.custom_query.CustomQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/custom-query")
public class CustomQueryController {

    @Autowired
    private CustomQueryService customQueryService;

    @GetMapping(path = "/data")
    Object getData(@RequestParam("id") String id, @RequestParam Map<String, String> parameters) {
        return customQueryService.getData(id, parameters);
    }

    @GetMapping(path = "/data-objects")
    Object getDataObjects(@RequestParam("id") String id, @RequestParam Map<String, String> parameters) {
        return customQueryService.getDataObjects(id, parameters);
    }

    @PostMapping(path = "/data")
    String postData(@RequestParam("id") String id, @RequestParam Map<String, String> parameters) {
        Object response = customQueryService.postData(id, parameters);
        return "{\"response\": \"" + response.toString() + "\"}";
    }

}
