package com.crm.sofia.controllers.sofia.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.chart.ChartFieldDTO;
import com.crm.sofia.services.sofia.chart.ChartDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class QueryDTO{
    public String query;
}

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

    @PostMapping(path = "/generate-data-fields")
    List<ChartFieldDTO> generateDataFields(@RequestBody QueryDTO dto) {
        return this.chartDesignerService.generateDataFields(dto.query);
    }



    @GetMapping(path = "/data")
    List<ChartFieldDTO> getData(@RequestParam("id") Long id) {
        return this.chartDesignerService.getData(id);
    }

}
