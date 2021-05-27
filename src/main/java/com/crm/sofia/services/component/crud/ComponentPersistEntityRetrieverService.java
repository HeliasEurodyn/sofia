package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.component.ComponentPersistEntityMapper;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.repository.component.ComponentPersistEntityRepository;
import com.crm.sofia.native_repository.component.ComponentPersistEntityRetrieverNativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentPersistEntityRetrieverService {

    private final ComponentPersistEntityRepository componentPersistEntityRepository;
    private final ComponentPersistEntityMapper componentPersistEntityMapper;
    private final ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository;

    public ComponentPersistEntityRetrieverService(ComponentPersistEntityRepository componentPersistEntityRepository,
                                                  ComponentPersistEntityMapper componentPersistEntityMapper,
                                                  ComponentPersistEntityRetrieverNativeRepository componentPersistEntityRetrieverNativeRepository) {
        this.componentPersistEntityRepository = componentPersistEntityRepository;
        this.componentPersistEntityMapper = componentPersistEntityMapper;
        this.componentPersistEntityRetrieverNativeRepository = componentPersistEntityRetrieverNativeRepository;
    }

    public ComponentPersistEntityDTO get(Long id, String selectionId){
        ComponentPersistEntityDTO componentPersistEntityDTO = this.getComponentPersistEntityById(id);

        List<ComponentPersistEntityFieldDTO> retrievalFieldList = componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() == null ? false : x.getPersistEntityField().getPrimaryKey()))
                .collect(Collectors.toList());

        retrievalFieldList
                .stream()
                .forEach(x -> x.setLocateStatement(selectionId));

        componentPersistEntityDTO = this.componentPersistEntityRetrieverNativeRepository
                .retrieveComponentPersistEntity(componentPersistEntityDTO,
                        retrievalFieldList);

        return componentPersistEntityDTO;
    }


    private ComponentPersistEntityDTO getComponentPersistEntityById(Long id) {
        Optional<ComponentPersistEntity> optionalComponentPersistEntity = componentPersistEntityRepository.findById(id);

        if (!optionalComponentPersistEntity.isPresent()) {
            return null;
        }

        ComponentPersistEntity componentPersistEntity = optionalComponentPersistEntity.get();
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentPersistEntityMapper.map(componentPersistEntity);

        return componentPersistEntityDTO;
    }

}
