package com.crm.sofia.controllers.cityscape.countermeasure;

import com.crm.sofia.model.cityscape.countermeasure.Countermeasure;
import com.crm.sofia.services.cityscape.coutermeasure.CountermeasureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping( "/countermeasure")
@Tag(name = "Countermeasures", description = "Endpoints collection for Countermeasure CRUD operations.")
public class CounermeasureController {

    private final CountermeasureService countermeasureService;

    public CounermeasureController(CountermeasureService countermeasureService) {
        this.countermeasureService = countermeasureService;
    }

    @GetMapping(path = "")
    public List<Countermeasure> getObjectById() {
        return this.countermeasureService.getObject();
    }

    @GetMapping(value = "/{id}")
    public Countermeasure getObjectById(@PathVariable("id") Long id) {
        return this.countermeasureService.getObject(id);
    }

    @PostMapping
    public Countermeasure postObject(@RequestBody Countermeasure countermeasure) {
        return this.countermeasureService.postObject(countermeasure);
    }

    @PutMapping
    public Countermeasure putObject(@RequestBody Countermeasure countermeasure) {
        return this.countermeasureService.postObject(countermeasure);
    }

    @DeleteMapping
    public Boolean delete(@RequestParam("id") Long id) {
        return this.countermeasureService.delete(id);
    }

}
