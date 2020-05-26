package com.crm.sofia.controllers.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.services.component.ComponentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/component")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    List<ComponentDTO> getObject() {
        return this.componentService.getObject();
    }

    @GetMapping(path="/by-id")
    ComponentDTO getObject(@RequestParam("id") Long id) {
        return this.componentService.getObject(id);
    }

    @PostMapping
    public ComponentDTO postObject(@RequestBody ComponentDTO dto) {
        ComponentDTO createdDTO = this.componentService.postObject(dto);
        return createdDTO;
    }


    @PutMapping
    public ComponentDTO putObject(@RequestBody ComponentDTO dto) {
        ComponentDTO createdDTO = this.componentService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.componentService.deleteObject(id);
    }

}
