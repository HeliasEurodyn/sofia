package com.crm.sofia.controllers.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/info")
public class InfoController {

    @GetMapping(value = "/version")
    Map getVersion() {
        return Collections.singletonMap("version", "1.1");
    }

}
