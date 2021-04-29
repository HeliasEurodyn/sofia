package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentPersistEntityMapper;
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
public class ComponentService {

    private final ComponentMapper componentMapper;
    private final ComponentRepository componentRepository;
    private final ComponentPersistEntityRepository componentPersistEntityRepository;
    private final ComponentPersistEntityMapper componentPersistEntityMapper;

    public ComponentService(ComponentMapper menuMapper,
                            ComponentRepository componentRepository,
                            ComponentFieldService componentFieldService,
                            ComponentPersistEntityRepository componentPersistEntityRepository,
                            ComponentPersistEntityMapper componentPersistEntityMapper
    ) {
        this.componentMapper = menuMapper;
        this.componentRepository = componentRepository;
        this.componentPersistEntityRepository = componentPersistEntityRepository;
        this.componentPersistEntityMapper = componentPersistEntityMapper;

    }

    public List<ComponentDTO> getObject() {
        List<Component> entites = this.componentRepository.findAll();
        entites = entites.stream().sorted((o1, o2) -> o1.getCreatedOn().compareTo(o2.getCreatedOn()))
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

    public ComponentDTO mapParametersToComponentDTO(ComponentDTO componentDTO, Map<String, Map<String, Object>> parameters) {


        List<ComponentPersistEntityDTO> componentPersistEntityList
                = this.setComponentPersistEntityTreeToList(componentDTO.getComponentPersistEntityList(), new ArrayList<>());

      //  this.mapParametersToComponentPersistEntityList(componentPersistEntityList, parameters);

        /* Iterate parameters */
        for (Map.Entry persistEntityPair : parameters.entrySet()) {
            String persistEntityCode = (String) persistEntityPair.getKey();
            Map<String, Object> persistEntityFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            /* Find component Persist entity from list */
            ComponentPersistEntityDTO componentPersistEntity = this.findComponentPersistEntity(componentPersistEntityList,
                    persistEntityCode);
            if (componentPersistEntity == null) continue;

//            if (persistEntityFieldsMap.containsKey("multiline-entity")) {
            if ((componentPersistEntity.getMultiDataLine()==null?false:componentPersistEntity.getMultiDataLine())) {
                /* Iterate parameters and map MultilinePersistEntity */
                componentPersistEntity = this.mapMultilinePersistEntity(componentPersistEntity,
                        persistEntityFieldsMap);
//                componentPersistEntity.setMultiDataLine(true);
            } else {
                /* Iterate parameters and map to componentPersistEntity Fields */
                List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                        this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntity.getComponentPersistEntityFieldList(),
                                persistEntityFieldsMap);
                componentPersistEntity.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
                componentPersistEntity.setMultiDataLine(false);
            }

        }

        return componentDTO;
    }

//    public ComponentDTO mapParametersToComponentPersistEntityList(List<ComponentPersistEntityDTO> componentPersistEntityList,
//                                                                  Map<String, Map<String, Object>> parameters) {
//
//        /* Iterate parameters */
//        for (Map.Entry persistEntityPair : parameters.entrySet()) {
//            String persistEntityCode = (String) persistEntityPair.getKey();
//            Map<String, Object> persistEntityFieldsMap = (Map<String, Object>) persistEntityPair.getValue();
//
//            /* Find component Persist entity from list */
//            ComponentPersistEntityDTO componentPersistEntity = this.findComponentPersistEntity(componentPersistEntityList,
//                    persistEntityCode);
//            if (componentPersistEntity == null) continue;
//
//            if (persistEntityFieldsMap.containsKey("multiline")) {
//                /* Iterate parameters and map MultilinePersistEntity */
//                componentPersistEntity = this.mapMultilinePersistEntity(componentPersistEntity,
//                        persistEntityFieldsMap);
//                componentPersistEntity.setMultiDataLine(true);
//            } else {
//                /* Iterate parameters and map to componentPersistEntity Fields */
//                List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
//                        this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntity.getComponentPersistEntityFieldList(),
//                                persistEntityFieldsMap);
//                componentPersistEntity.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
//                componentPersistEntity.setMultiDataLine(false);
//            }
//
//        }
//
//        return componentDTO;
//    }


    private ComponentPersistEntityDTO findComponentPersistEntity(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                 String persistEntityCode) {

//        List<ComponentPersistEntityDTO> componentPersistEntityList
//                = this.setComponentPersistEntityTreeToList(componentPersistEntityTree, new ArrayList<>());

        Optional<ComponentPersistEntityDTO> componentPersistEntityOptional =
                componentPersistEntityList
                        .stream()
                        .filter(cpe -> cpe.getCode().equals(persistEntityCode)).findFirst();

        if (!componentPersistEntityOptional.isPresent()){
            return null;
        }

        return componentPersistEntityOptional.get();
    }

    private  List<ComponentPersistEntityDTO> setComponentPersistEntityTreeToList(
            List<ComponentPersistEntityDTO> componentPersistEntityTree,
            List<ComponentPersistEntityDTO> componentPersistEntityList) {

        componentPersistEntityList.addAll(componentPersistEntityTree);

        componentPersistEntityTree
                .stream()
                .filter(componentPersistEntity -> componentPersistEntity.getComponentPersistEntityList() != null)
                .forEach(componentPersistEntity -> {
                    this.setComponentPersistEntityTreeToList(
                            componentPersistEntity.getComponentPersistEntityList(),componentPersistEntityList);
                });

        return componentPersistEntityList;
    }

    private ComponentPersistEntityDTO mapMultilinePersistEntity(ComponentPersistEntityDTO componentPersistEntity, Map<String, Object> persistEntityFieldsMap) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {
        }.getType();

        /* Iterate lines */
        for (Map.Entry persistEntityPair : persistEntityFieldsMap.entrySet()) {
            String persistEntityid = (String) persistEntityPair.getKey();
            if (persistEntityid == "multiline-entity") continue;

            Map<String, Object> persistEntityLineFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            /* Clone ComponentPersistEntityFieldList and add to line */
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityFieldList()), listType);

            /* Iterate parameters and map to componentPersistEntity Fields */
            componentPersistEntityFieldList =
                    this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntityFieldList,
                            persistEntityLineFieldsMap);

            ComponentPersistEntityDataLineDTO componentPersistEntityDataLine = new ComponentPersistEntityDataLineDTO();
            componentPersistEntityDataLine.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntity.getComponentPersistEntityDataLines().add(componentPersistEntityDataLine);
        }

        return componentPersistEntity;
    }

    private List<ComponentPersistEntityFieldDTO>
    itterateAndMapPamametersToComponentPersistEntityFields(
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            Map<String, Object> persistEntityFieldsMap) {
        /* Iterate parameters */
        for (Map.Entry persistEntityFieldPair : persistEntityFieldsMap.entrySet()) {
            String persistEntityFieldCode = (String) persistEntityFieldPair.getKey();
            Object persistEntityFieldValue = (Object) persistEntityFieldPair.getValue();

            /* Find component Persist entity field */
            ComponentPersistEntityFieldDTO compPersistEntityField =
                    this.findComponentPersistEntityField(componentPersistEntityFieldList,
                            persistEntityFieldCode);
            if (compPersistEntityField == null) continue;

            /* Set value to compPersistEntityField */
            compPersistEntityField.setValue(persistEntityFieldValue);
        }

        return componentPersistEntityFieldList;
    }

    private ComponentPersistEntityFieldDTO findComponentPersistEntityField(List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                                           String persistEntityFieldCode) {

        /* Find component Persist entity field from selected componentPersistEntity */
        Optional<ComponentPersistEntityFieldDTO> compPersistEntityFieldOptional =
                componentPersistEntityFieldList
                        .stream()
                        .filter(cpef -> cpef.getPersistEntityField().getName().equals(persistEntityFieldCode))
                        .findFirst();

        if (!compPersistEntityFieldOptional.isPresent()) return null;
        ComponentPersistEntityFieldDTO compPersistEntityField = compPersistEntityFieldOptional.get();

        return compPersistEntityField;
    }

    public Boolean hasPrimaryKeyValue(ComponentDTO component) {

        if (component.getComponentPersistEntityList() == null) {
            return false;
        }

        if (component.getComponentPersistEntityList().size() == 0) {
            return false;
        }

        ComponentPersistEntityDTO componentPersistEntity = component.getComponentPersistEntityList().get(0);

        Optional<ComponentPersistEntityFieldDTO> optionalComponentPersistEntityFieldDTO =
                componentPersistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(componentPersistEntityField -> componentPersistEntityField.getPersistEntityField().getPrimaryKey() == true).findFirst();

        if (!optionalComponentPersistEntityFieldDTO.isPresent()) {
            return false;
        }

        ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO = optionalComponentPersistEntityFieldDTO.get();

        if (componentPersistEntityFieldDTO.getValue() == null) {
            return false;
        } else {
            return true;
        }

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
                            .filter(cpe -> cpe.getPersistEntity() != null )
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
                            .filter(cpe -> cpe.getPersistEntity() != null )
                            .filter(cpe -> cpe.getPersistEntity().getId() == persistEntity.getId())
                            .forEach(cpe -> {

                                /* Add Fields */
                                List<Long> currentTableFieldIds =  cpe.getComponentPersistEntityFieldList()
                                        .stream()
                                        .map(cpef -> cpef.getPersistEntityField().getId())
                                        .collect(Collectors.toList());

                                persistEntity.getPersistEntityFieldList()
                                        .stream()
                                        .filter(pf -> !currentTableFieldIds.contains(pf.getId()) )
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
