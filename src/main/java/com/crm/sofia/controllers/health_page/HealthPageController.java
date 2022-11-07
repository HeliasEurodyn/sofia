package com.crm.sofia.controllers.health_page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/health-page")
public class HealthPageController {

    @GetMapping
    String getObject() {
        return "ok";
    }

}
