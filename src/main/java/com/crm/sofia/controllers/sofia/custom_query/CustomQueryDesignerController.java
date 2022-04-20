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
@RequestMapping("/custom-query-designer")
public class CustomQueryDesignerController {

    @Autowired
    private CustomQueryService customQueryService;

    @GetMapping
    List<CustomQueryDTO> getObject() {
        return customQueryService.getObject();
    }

    @GetMapping(path = "/by-id")
    CustomQueryDTO getObject(@RequestParam("id") String id) {
        return customQueryService.getObject(id);
    }

    @GetMapping(path = "/data")
    Object getData(@RequestParam("id") String id, @RequestParam Map<String, String> parameters) {
        return customQueryService.getData(id, parameters);
    }

    @PostMapping
    public CustomQueryDTO postObject(@RequestBody CustomQueryDTO customQueryDto) throws IOException {
        return customQueryService.postObject(customQueryDto);
    }

    @PutMapping
    public CustomQueryDTO putObject(@RequestBody CustomQueryDTO customQueryDto) {
        return customQueryService.postObject(customQueryDto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        customQueryService.deleteObject(id);
    }

}
