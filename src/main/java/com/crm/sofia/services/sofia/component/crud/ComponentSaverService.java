package com.crm.sofia.services.sofia.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.native_repository.sofia.component.ComponentSaverNativeRepository;
import com.crm.sofia.services.sofia.component.ComponentService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ComponentSaverService {

    private final ComponentSaverNativeRepository componentSaverNativeRepository;
    private final ComponentService componentService;

    public ComponentSaverService(ComponentSaverNativeRepository componentSaverNativeRepository,
                                 ComponentService componentService) {
        this.componentSaverNativeRepository = componentSaverNativeRepository;
        this.componentService = componentService;
    }

    public String save(String componentId, Map<String, Map<String, Object>> parameters) {
        /* Retrieve form from Database */
        ComponentDTO componentDTO = componentService.getObject(componentId);

        /* Map Parameters and Save */
        return this.save(componentDTO, parameters);
    }

    public String save(ComponentDTO componentDTO, Map<String, Map<String, Object>> parameters) {

        /* Μap parameters to component */
        componentService.mapParametersToComponentDTO(componentDTO.getComponentPersistEntityList(), parameters);

        /* Save */
        return this.save(componentDTO);
    }

    public String save(ComponentDTO componentDTO) {

        /* Save */
        return this.componentSaverNativeRepository.save(componentDTO);
    }
}
