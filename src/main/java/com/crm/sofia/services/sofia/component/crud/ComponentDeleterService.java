package com.crm.sofia.services.sofia.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.native_repository.sofia.component.ComponentDeleterNativeRepository;
import com.crm.sofia.native_repository.sofia.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.services.sofia.component.ComponentService;
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

        this.retrieveComponentAndDelete(componentDTO, selectionId);
    }

    public void retrieveComponentAndDelete(ComponentDTO componentDTO, String selectionId) {

        /* Retrieve component data from Database */
        this.componentRetrieverNativeRepository.retrieveComponentData(componentDTO, selectionId);

        /* delete */
        this.componentDeleterNativeRepository.delete(componentDTO);
    }


}
