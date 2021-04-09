package com.crm.sofia.controllers.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.form.FormDynamicQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Validated
@RequestMapping("/component")
public class ComponentController {

    private final ComponentService componentService;
    private final FormDynamicQueryService formDynamicQueryService;

    public ComponentController(ComponentService componentService,
                               FormDynamicQueryService formDynamicQueryService) {
        this.componentService = componentService;
        this.formDynamicQueryService = formDynamicQueryService;
    }

    @GetMapping
    List<ComponentDTO> getObject() {
        return this.componentService.getObject();
    }

    @GetMapping(path = "/by-id")
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

    @GetMapping(path = "/component-persist-entity/data/by-id")
    ComponentPersistEntityDTO getComponentPersistEntityDataById(@RequestParam("component-persist-entity-id") Long id,
                                                                @RequestParam("selection-id") String selectionId) {
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentService.getComponentPersistEntityDataById(id, selectionId);

        List<ComponentPersistEntityFieldDTO> retrievalFieldList = componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() == null ? false : x.getPersistEntityField().getPrimaryKey()))
                .collect(Collectors.toList());

        retrievalFieldList
                .stream()
                .forEach(x -> x.setLocateStatement(selectionId));

        componentPersistEntityDTO = this.formDynamicQueryService.retrieveComponentPersistEntity(componentPersistEntityDTO,
                retrievalFieldList);

        return componentPersistEntityDTO;
    }

}
