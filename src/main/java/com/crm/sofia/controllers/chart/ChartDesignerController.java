package com.crm.sofia.controllers.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.chart.ChartFieldDTO;
import com.crm.sofia.dto.sofia.chart.ChartQueryDTO;
import com.crm.sofia.services.chart.ChartDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
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
    ChartDTO getObject(@RequestParam("id") String id) {
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
    public void deleteObject(@RequestParam("id") String id) {
        this.chartDesignerService.deleteObject(id);
    }

    @PostMapping(path = "/generate-data-fields")
    List<ChartFieldDTO> generateDataFields(@RequestBody ChartQueryDTO dto) {
        return this.chartDesignerService.generateDataFields(new String(Base64.getDecoder().decode(dto.query)));
    }

    @GetMapping(path = "/data")
    List<ChartFieldDTO> getData(@RequestParam("id") String id) {
        return this.chartDesignerService.getData(id);
    }

}
