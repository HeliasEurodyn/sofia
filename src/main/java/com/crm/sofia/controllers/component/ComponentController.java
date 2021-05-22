package com.crm.sofia.controllers.component;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.component.dynamic_query.ComponentPersistEntityRetrieverService;
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
    private final ComponentPersistEntityRetrieverService componentPersistEntityRetrieverService;

    public ComponentController(ComponentService componentService,
                               ComponentPersistEntityRetrieverService componentPersistEntityRetrieverService) {
        this.componentService = componentService;
        this.componentPersistEntityRetrieverService = componentPersistEntityRetrieverService;
    }

    @GetMapping(path = "/component-persist-entity/by-id")
    ComponentPersistEntityDTO getComponentPersistEntityDataById(@RequestParam("component-persist-entity-id") Long id,
                                                                @RequestParam("selection-id") String selectionId) {
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentService.getComponentPersistEntityById(id);

        List<ComponentPersistEntityFieldDTO> retrievalFieldList = componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() == null ? false : x.getPersistEntityField().getPrimaryKey()))
                .collect(Collectors.toList());

        retrievalFieldList
                .stream()
                .forEach(x -> x.setLocateStatement(selectionId));

        componentPersistEntityDTO = this.componentPersistEntityRetrieverService
                .retrieveComponentPersistEntity(componentPersistEntityDTO,
                retrievalFieldList);

        return componentPersistEntityDTO;
    }

}
