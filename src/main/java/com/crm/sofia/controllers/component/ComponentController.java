package com.crm.sofia.controllers.component;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.native_repository.component.ComponentPersistEntityRetrieverNativeRepository;
import com.crm.sofia.services.component.crud.ComponentDeleterService;
import com.crm.sofia.services.component.crud.ComponentPersistEntityRetrieverService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
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
    ComponentPersistEntityDTO getComponentPersistEntityDataById(@RequestParam("component-persist-entity-id") String id,
                                                                @RequestParam("selection-id") String selectionId) {
        return this.componentPersistEntityRetrieverService.getComponentPersistEntityDataById(id, selectionId);
    }

    @GetMapping(path = "/by-id")
    ComponentDTO getObject(@RequestParam("id") String componentId,
                           @RequestParam("selection-id") String selectionId) {
        return this.componentRetrieverService.retrieveComponentWithData(componentId, selectionId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") String componentId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.componentSaverService.save(componentId, parameters);
    }

    @DeleteMapping
    public void deleteObjectData(@RequestParam("id") String componentId, @RequestParam("selection-id") String selectionId) {
        this.componentDeleterService.retrieveComponentAndDelete(componentId, selectionId);
    }


}
