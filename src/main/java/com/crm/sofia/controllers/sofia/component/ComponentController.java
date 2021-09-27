package com.crm.sofia.controllers.sofia.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.services.sofia.component.crud.ComponentDeleterService;
import com.crm.sofia.services.sofia.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.sofia.component.crud.ComponentPersistEntityRetrieverService;
import com.crm.sofia.services.sofia.component.crud.ComponentSaverService;
import com.crm.sofia.native_repository.sofia.component.ComponentPersistEntityRetrieverNativeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/component")
public class ComponentController {

    private final ComponentPersistEntityRetrieverService componentPersistEntityRetrieverService;
    private final ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository;
    private final ComponentSaverService componentSaverService;
    private final ComponentDeleterService componentDeleterService;
    private final ComponentRetrieverService componentRetrieverService;

    public ComponentController(ComponentPersistEntityRetrieverService componentPersistEntityRetrieverService,
                               ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository,
                               ComponentSaverService componentSaverService,
                               ComponentDeleterService componentDeleterService,
                               ComponentRetrieverService componentRetrieverService) {
        this.componentPersistEntityRetrieverService = componentPersistEntityRetrieverService;
        this.componentPersistEntityRetrieverNativeRepository = componentPersistEntityRetrieverNativeRepository;
        this.componentSaverService = componentSaverService;
        this.componentDeleterService = componentDeleterService;
        this.componentRetrieverService = componentRetrieverService;
    }

    @GetMapping(path = "/component-persist-entity/by-id")
    ComponentPersistEntityDTO getComponentPersistEntityDataById(@RequestParam("component-persist-entity-id") Long id,
                                                                @RequestParam("selection-id") String selectionId) {
        return this.componentPersistEntityRetrieverService.get(id, selectionId);
    }


    @GetMapping(path = "/by-id")
    ComponentDTO getObject(@RequestParam("id") Long componentId,
                           @RequestParam("selection-id") String selectionId) {
        return this.componentRetrieverService.retrieveComponentWithData(componentId, selectionId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") Long componentId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.componentSaverService.save(componentId, parameters);
    }

    @DeleteMapping
    public void deleteObjectData(@RequestParam("id") Long componentId, @RequestParam("selection-id") String selectionId) {
        this.componentDeleterService.retrieveComponentAndDelete(componentId, selectionId);
    }


}