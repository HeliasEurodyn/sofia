package com.crm.sofia.services.sofia.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.sofia.component.ComponentPersistEntityMapper;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import com.crm.sofia.native_repository.sofia.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.repository.sofia.component.ComponentPersistEntityRepository;
//import com.crm.sofia.native_repository.sofia.component.ComponentPersistEntityRetrieverNativeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentPersistEntityRetrieverService {

    private final ComponentPersistEntityRepository componentPersistEntityRepository;
    private final ComponentPersistEntityMapper componentPersistEntityMapper;
//    private final ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository;
    private final ComponentRetrieverNativeRepository componentRetrieverNativeRepository;

    public ComponentPersistEntityRetrieverService(ComponentPersistEntityRepository componentPersistEntityRepository,
                                                  ComponentPersistEntityMapper componentPersistEntityMapper,
//                                                  ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository,
                                                    ComponentRetrieverNativeRepository componentRetrieverNativeRepository) {
        this.componentPersistEntityRepository = componentPersistEntityRepository;
        this.componentPersistEntityMapper = componentPersistEntityMapper;
//        this.componentPersistEntityRetrieverNativeRepository = componentPersistEntityRetrieverNativeRepository;
        this.componentRetrieverNativeRepository = componentRetrieverNativeRepository;
    }

    public ComponentPersistEntityDTO get(String id, String selectionId){
        ComponentPersistEntityDTO componentPersistEntityDTO = this.getComponentPersistEntityById(id);

        List<ComponentPersistEntityFieldDTO> retrievalFieldList = componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() == null ? false : x.getPersistEntityField().getPrimaryKey()))
                .collect(Collectors.toList());

        retrievalFieldList
                .stream()
                .forEach(x -> x.setLocateStatement(selectionId));

//        componentPersistEntityDTO = this.componentPersistEntityRetrieverNativeRepository
//                .retrieveComponentPersistEntity(componentPersistEntityDTO,
//                        retrievalFieldList);

        this.componentRetrieverNativeRepository.retrieveComponentPersistEntityListData(
                Collections.singletonList(componentPersistEntityDTO),
                new ArrayList<>());

        return componentPersistEntityDTO;
    }


    private ComponentPersistEntityDTO getComponentPersistEntityById(String id) {
        Optional<ComponentPersistEntity> optionalComponentPersistEntity = componentPersistEntityRepository.findById(id);

        if (!optionalComponentPersistEntity.isPresent()) {
            return null;
        }

        ComponentPersistEntity componentPersistEntity = optionalComponentPersistEntity.get();
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentPersistEntityMapper.map(componentPersistEntity);

        return componentPersistEntityDTO;
    }

}
