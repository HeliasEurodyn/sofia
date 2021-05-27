package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentPersistEntityMapper;
import com.crm.sofia.model.common.BaseNoIdEntity;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.crm.sofia.model.persistEntity.PersistEntity;
import com.crm.sofia.repository.component.ComponentPersistEntityRepository;
import com.crm.sofia.repository.component.ComponentRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.*;
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
        entites = entites.stream().sorted(Comparator.comparing(BaseNoIdEntity::getCreatedOn))
                .collect(Collectors.toList());
        return this.componentMapper.map(entites);
    }

    public ComponentDTO getObject(Long id) {
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
        return this.componentMapper.map(createdEntity);
    }

    @Transactional
    public ComponentDTO putObject(ComponentDTO dto) {
        Component entity = this.componentMapper.map(dto);
        Component createdEntity = this.componentRepository.save(entity);
        return this.componentMapper.map(createdEntity);
    }

    public void deleteObject(Long id) {
        this.componentRepository.deleteById(id);
    }

    public ComponentPersistEntityDTO getComponentPersistEntityDataById(Long id, String selectionId) {
        ComponentPersistEntityDTO componentPersistEntityDTO = this.getComponentPersistEntityById(id);
        return componentPersistEntityDTO;
    }

    public ComponentPersistEntityDTO getComponentPersistEntityById(Long id) {
        Optional<ComponentPersistEntity> optionalComponentPersistEntity = componentPersistEntityRepository.findById(id);

        if (!optionalComponentPersistEntity.isPresent()) {
            return null;
        }

        ComponentPersistEntity componentPersistEntity = optionalComponentPersistEntity.get();
        ComponentPersistEntityDTO componentPersistEntityDTO = this.componentPersistEntityMapper.map(componentPersistEntity);

        return componentPersistEntityDTO;
    }

    public void removeComponentTablesByTableId(Long persistEntityId) {
        List<Component> components = this.componentRepository.findComponentsThatContainTable(persistEntityId);

        components
                .forEach(c -> {
                    c.getComponentPersistEntityList()
                            .removeIf(cpe -> persistEntityId == cpe.getPersistEntity().getId());
                });

        this.componentRepository.saveAll(components);
    }

    public void removeComponentTableFieldsByTable(Long persistEntityId, List<Long> tableFieldIds) {
        List<Component> components = this.componentRepository.findComponentsThatContainTable(persistEntityId);

        components
                .forEach(c -> {
                    c.getComponentPersistEntityList()
                            .stream()
                            .filter(cpe -> cpe.getPersistEntity() != null)
                            .filter(cpe -> cpe.getPersistEntity().getId() == persistEntityId)
                            .forEach(cpe -> {

                                /* Remove Fields */
                                cpe.getComponentPersistEntityFieldList()
                                        .removeIf(cpef -> !tableFieldIds.contains(cpef.getPersistEntityField().getId()));
                            });
                });

        this.componentRepository.saveAll(components);
    }

    public void insertComponentTableFieldsByTable(PersistEntity persistEntity) {
        List<Component> components = this.componentRepository.findComponentsThatContainTable(persistEntity.getId());

        components
                .forEach(c -> {
                    c.getComponentPersistEntityList()
                            .stream()
                            .filter(cpe -> cpe.getPersistEntity() != null)
                            .filter(cpe -> cpe.getPersistEntity().getId() == persistEntity.getId())
                            .forEach(cpe -> {

                                /* Add Fields */
                                List<Long> currentTableFieldIds = cpe.getComponentPersistEntityFieldList()
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
                });

        this.componentRepository.saveAll(components);
    }
}
