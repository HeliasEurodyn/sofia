package com.crm.sofia.controllers.sofia.custom_query;

import com.crm.sofia.dto.sofia.custom_query.CustomQueryDTO;
import com.crm.sofia.services.sofia.custom_query.CustomQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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

}
