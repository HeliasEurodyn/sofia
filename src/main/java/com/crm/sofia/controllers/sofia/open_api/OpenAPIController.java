package com.crm.sofia.controllers.sofia.open_api;

import com.crm.sofia.dto.sofia.component.user.ComponentUiDTO;
import com.crm.sofia.services.sofia.open_api.OpenAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/open-api")
public class OpenAPIController {

    private final OpenAPIService openAPIService;

    public OpenAPIController(OpenAPIService openAPIService) {
        this.openAPIService = openAPIService;
    }

    @GetMapping(produces = "text/javascript;")
    String getYaml() {
        return this.openAPIService.generateYaml();
    }

}
