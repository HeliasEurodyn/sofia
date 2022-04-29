package com.crm.sofia.services.sofia.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.mapper.sofia.component.ComponentMapper;
import com.crm.sofia.mapper.sofia.component.ComponentPersistEntityMapper;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.component.Component;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityField;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.repository.sofia.component.ComponentPersistEntityRepository;
import com.crm.sofia.repository.sofia.component.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentDesignerService {

    private final ComponentMapper componentMapper;
    private final ComponentRepository componentRepository;
    private final ComponentPersistEntityRepository componentPersistEntityRepository;
    private final ComponentPersistEntityMapper componentPersistEntityMapper;

    public ComponentDesignerService(ComponentMapper menuMapper,
                                    ComponentRepository componentRepository,
                                    ComponentPersistEntityRepository componentPersistEntityRepository,
                                    ComponentPersistEntityMapper componentPersistEntityMapper
    ) {
        this.componentMapper = menuMapper;
        this.componentRepository = componentRepository;
        this.componentPersistEntityRepository = componentPersistEntityRepository;
        this.componentPersistEntityMapper = componentPersistEntityMapper;
    }

    public List<ComponentDTO> getList() {
        List<Component> entites = this.componentRepository.findAll();
        entites = entites.stream().sorted(Comparator.comparing(MainEntity::getCreatedOn))
                .collect(Collectors.toList());
        return this.componentMapper.map(entites);
    }

    public ComponentDTO getObject(String id) {
        Optional<Component> optionalEntity = this.componentRepository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Component entity = optionalEntity.get();
        ComponentDTO dto = this.componentMapper.map(entity);
        List<ComponentPersistEntityDTO> sorted = dto.getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
        dto.setComponentPersistEntityList(sorted);

        return dto;
    }

    @Transactional
    public ComponentDTO postObject(ComponentDTO dto) {
        Component entity = this.componentMapper.mapWithPersistEntities(dto);

        Component createdEntity = this.componentRepository.save(entity);
        //return this.componentMapper.map(createdEntity);
        return null;
    }

    @Transactional
    public ComponentDTO putObject(ComponentDTO dto) {
        Component entity = this.componentMapper.map(dto);
        Component createdEntity = this.componentRepository.save(entity);
        return this.componentMapper.map(createdEntity);
    }

    public void deleteObject(String id) {
        this.componentRepository.deleteById(id);
    }

    public ComponentPersistEntityDTO getComponentPersistEntityDataById(String id, String selectionId) {
        ComponentPersistEntityDTO componentPersistEntityDTO = this.getComponentPersistEntityById(id);
        return componentPersistEntityDTO;
    }

    public ComponentPersistEntityDTO getComponentPersistEntityById(String id) {
        Optional<ComponentPersistEntity> optionalComponentPersistEntity = componentPersistEntityRepository.findById(id);

        if (!optionalComponentPersistEntity.isPresent()) {
            return null;
        }

        ComponentPersistEntity componentPersistEntity = optionalComponentPersistEntity.get();
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentPersistEntityMapper.map(componentPersistEntity);

        return componentPersistEntityDTO;
    }

    public void removeComponentTablesByTableId(String persistEntityId) {
        List<ComponentPersistEntity> componentPersistEntities =
                this.componentPersistEntityRepository.findComponentEntitiesOfTableId(persistEntityId);

        this.componentPersistEntityRepository.deleteAll(componentPersistEntities);
    }

    public void removeComponentTableFieldsByTable(String persistEntityId, List<String> tableFieldIds) {
        List<ComponentPersistEntity> componentPersistEntities = this.componentPersistEntityRepository.findComponentEntitiesOfTableId(persistEntityId);


        componentPersistEntities
                .stream()
                .filter(cpe -> cpe.getPersistEntity() != null)
                .filter(cpe -> cpe.getPersistEntity().getId().equals(persistEntityId))
                .forEach(cpe -> {

                    /* Remove Fields */
                    cpe.getComponentPersistEntityFieldList()
                            .removeIf(cpef -> !tableFieldIds.contains(cpef.getPersistEntityField().getId()));
                });

        this.componentPersistEntityRepository.saveAll(componentPersistEntities);
    }

    public void insertComponentTableFieldsByTable(PersistEntity persistEntity) {

        List<ComponentPersistEntity> componentPersistEntities = this.componentPersistEntityRepository.findComponentEntitiesOfTableId(persistEntity.getId());

        componentPersistEntities
                .stream()
                .filter(cpe -> cpe.getPersistEntity() != null)
                .filter(cpe -> cpe.getPersistEntity().getId() == persistEntity.getId())
                .forEach(cpe -> {

                    /* Add Fields */
                    List<String> currentTableFieldIds = cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .map(cpef -> cpef.getPersistEntityField().getId())
                            .collect(Collectors.toList());

                    persistEntity.getPersistEntityFieldList()
                            .stream()
                            .filter(pf -> !currentTableFieldIds.contains(pf.getId()))
                            .forEach(pf -> {
                                ComponentPersistEntityField cpef = new ComponentPersistEntityField();
                                cpef.setPersistEntityField(pf);
                                cpe.getComponentPersistEntityFieldList().add(cpef);
                            });
                });

        this.componentPersistEntityRepository.saveAll(componentPersistEntities);
    }
}
