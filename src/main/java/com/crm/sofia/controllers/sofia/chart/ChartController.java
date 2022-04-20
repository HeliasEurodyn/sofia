package com.crm.sofia.controllers.sofia.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.chart.ChartFieldDTO;
import com.crm.sofia.services.sofia.chart.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    ChartDTO getObject(@RequestParam("id") String id,
                       @RequestParam Map<String, String> parameters) {
        return this.chartService.getObject(id, parameters);
    }

    @GetMapping(path = "/data")
    List<ChartFieldDTO> getData(@RequestParam("id") String id,
                                @RequestParam Map<String, String> parameters) {
        return this.chartService.getData(id, parameters);
    }

}
