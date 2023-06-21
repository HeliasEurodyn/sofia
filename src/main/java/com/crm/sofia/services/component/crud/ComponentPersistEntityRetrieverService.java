package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.component.ComponentPersistEntityMapper;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.repository.component.ComponentPersistEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComponentPersistEntityRetrieverService {
    @Autowired
    private  ComponentPersistEntityRepository componentPersistEntityRepository;
    @Autowired
    private  ComponentPersistEntityMapper componentPersistEntityMapper;
//    private final ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository;
    @Autowired
    private  ComponentRetrieverNativeRepository componentRetrieverNativeRepository;
    @Autowired
    public ComponentPersistEntityRetrieverService(ComponentPersistEntityRepository componentPersistEntityRepository,
                                                  ComponentPersistEntityMapper componentPersistEntityMapper,
//                                                  ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository,
                                                    ComponentRetrieverNativeRepository componentRetrieverNativeRepository) {
        this.componentPersistEntityRepository = componentPersistEntityRepository;
        this.componentPersistEntityMapper = componentPersistEntityMapper;
//        this.componentPersistEntityRetrieverNativeRepository = componentPersistEntityRetrieverNativeRepository;
        this.componentRetrieverNativeRepository = componentRetrieverNativeRepository;
    }

    public ComponentPersistEntityDTO getComponentPersistEntityDataById(String id, String selectionId){
        ComponentPersistEntityDTO componentPersistEntityDTO = this.getComponentPersistEntityById(id);

        /* Clear Other Locate Statements Since it Searches by the Id */
        componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .forEach(x -> x.setLocateStatement(""));

        /* Retrive as NOT Multiline since it searches by a specific Id */
        componentPersistEntityDTO.setMultiDataLine(false);

        List<ComponentPersistEntityFieldDTO> retrievalFieldList = componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() == null ? false : x.getPersistEntityField().getPrimaryKey()))
                .collect(Collectors.toList());

        retrievalFieldList
                .stream()
                .forEach(x -> x.setLocateStatement(selectionId));

        this.componentRetrieverNativeRepository.retrieveComponentPersistEntityListData(
                Collections.singletonList(componentPersistEntityDTO),
                new ArrayList<>());

        return componentPersistEntityDTO;
    }


    private ComponentPersistEntityDTO getComponentPersistEntityById(String id) {
        ComponentPersistEntity componentPersistEntity = componentPersistEntityRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Component Does Not Exist"));

        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentPersistEntityMapper.mapCpe(componentPersistEntity);

        return componentPersistEntityDTO;
    }

}
