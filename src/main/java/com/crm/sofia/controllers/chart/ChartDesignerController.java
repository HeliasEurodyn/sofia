package com.crm.sofia.controllers.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.services.chart.ChartDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/chart-designer")
public class ChartDesignerController {

    private final ChartDesignerService chartDesignerService;

    public ChartDesignerController(ChartDesignerService chartDesignerService) {
        this.chartDesignerService = chartDesignerService;
    }

    @GetMapping
    List<ChartDTO> getObject() {
        return this.chartDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    ChartDTO getObject(@RequestParam("id") Long id) {
        return this.chartDesignerService.getObject(id);
    }

    @PostMapping
    public ChartDTO postObject(@RequestBody ChartDTO dto) {
        return this.chartDesignerService.postObject(dto);
    }

    @PutMapping
    public ChartDTO putObject(@RequestBody ChartDTO dto) {
        return this.chartDesignerService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.chartDesignerService.deleteObject(id);
    }

    @GetMapping(path = "/generate-data-fields")
    List<ChartFieldDTO> generateDataFields(@RequestParam("query") String query) {
        return this.chartDesignerService.generateDataFields(query);
    }

    @GetMapping(path = "/data")
    List<ChartFieldDTO> getData(@RequestParam("id") Long id) {
        return this.chartDesignerService.getData(id);
    }

}
