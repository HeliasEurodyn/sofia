package com.crm.sofia.controllers.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.services.chart.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/chart")
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping(path = "/by-id")
    ChartDTO getObject(@RequestParam("id") Long id) {
        return this.chartService.getObject(id);
    }

}
