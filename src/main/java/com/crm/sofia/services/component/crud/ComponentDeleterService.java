package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.repository.component.ComponentRepository;
import com.crm.sofia.native_repository.component.ComponentDeleterNativeRepository;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.services.component.ComponentService;
import org.springframework.stereotype.Service;

@Service
public class ComponentDeleterService {

    private final ComponentService componentService;
    private final ComponentDeleterNativeRepository componentDeleterNativeRepository;
    private final ComponentRetrieverNativeRepository componentRetrieverNativeRepository;

    public ComponentDeleterService(ComponentService componentService,
                                   ComponentDeleterNativeRepository componentDeleterNativeRepository,
                                   ComponentRetrieverNativeRepository componentRetrieverNativeRepository) {
        this.componentService = componentService;
        this.componentDeleterNativeRepository = componentDeleterNativeRepository;
        this.componentRetrieverNativeRepository = componentRetrieverNativeRepository;
    }

    public void retrieveComponentAndDelete(Long componentId, String selectionId) {

        /* Retrieve component from Database */
        ComponentDTO componentDTO = componentService.getObject(componentId);

        /* Retrieve component data from Database */
        this.componentRetrieverNativeRepository.retrieveComponentData(componentDTO, selectionId);

        /* delete */
        this.componentDeleterNativeRepository.delete(componentDTO);

    }


}
