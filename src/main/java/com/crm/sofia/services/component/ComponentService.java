package com.crm.sofia.services.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.model.sofia.component.Component;
import com.crm.sofia.repository.component.ComponentRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;
    private final ComponentMapper componentMapper;

    public ComponentService(ComponentRepository componentRepository,
                            ComponentMapper componentMapper) {
        this.componentRepository = componentRepository;
        this.componentMapper = componentMapper;
    }

    public ComponentDTO getObject(String id) {
        Optional<Component> componentOptional = this.componentRepository.findById(id);
        if (!componentOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        ComponentDTO componentDTO = this.componentMapper.map(componentOptional.get());
        return componentDTO;
    }

    public void mapParametersToComponentDTO(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                             Map<String, Map<String, Object>> parameters) {

        /* Iterate parameters */
        for (Map.Entry persistEntityPair : parameters.entrySet()) {
            String persistEntityCode = (String) persistEntityPair.getKey();
            Map<String, Object> persistEntityFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            ComponentPersistEntityDTO componentPersistEntity =
                    this.findComponentPersistEntity(componentPersistEntityList, persistEntityCode);
            if (componentPersistEntity == null) continue;

            if ((componentPersistEntity.getMultiDataLine() == null ? false : componentPersistEntity.getMultiDataLine())) {
                this.mapMultilinePersistEntity(componentPersistEntity,
                        persistEntityFieldsMap);
            } else {
                this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntity.getComponentPersistEntityFieldList(),
                        persistEntityFieldsMap);

                if (persistEntityFieldsMap.containsKey("sub-entities") && componentPersistEntity.getComponentPersistEntityList() != null) {
                    this.mapParametersToComponentDTO(componentPersistEntity.getComponentPersistEntityList(),
                            (Map<String, Map<String, Object>>) persistEntityFieldsMap.get("sub-entities"));
                }
            }
        }
    }

    private ComponentPersistEntityDTO findComponentPersistEntity(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                 String persistEntityCode) {

        Optional<ComponentPersistEntityDTO> componentPersistEntityOptional =
                componentPersistEntityList
                        .stream()
                        .filter(cpe -> cpe.getCode().equals(persistEntityCode)).findFirst();

        if (!componentPersistEntityOptional.isPresent()) {
            return null;
        }

        return componentPersistEntityOptional.get();
    }

    private ComponentPersistEntityDTO mapMultilinePersistEntity(ComponentPersistEntityDTO componentPersistEntity, Map<String, Object> persistEntityFieldsMap) {

        Gson gson = new Gson();
        Type fieldListType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {
        }.getType();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityDTO>>() {
        }.getType();
        /* Iterate lines */
        for (Map.Entry persistEntityPair : persistEntityFieldsMap.entrySet()) {

            Map<String, Object> persistEntityLineFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            /* Clone ComponentPersistEntityFieldList and add to line */
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityFieldList()), fieldListType);

            /* Clone ComponentPersistEntity and add to line */
            List<ComponentPersistEntityDTO> componentPersistEntityList =
                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityList()), listType);

            /* Iterate parameters and map to componentPersistEntity Fields */
            componentPersistEntityFieldList =
                    this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntityFieldList,
                            persistEntityLineFieldsMap);

            if (persistEntityLineFieldsMap.containsKey("sub-entities") && componentPersistEntityList != null) {
                this.mapParametersToComponentDTO(componentPersistEntityList,
                        (Map<String, Map<String, Object>>) persistEntityLineFieldsMap.get("sub-entities"));
            }

            ComponentPersistEntityDataLineDTO componentPersistEntityDataLine = new ComponentPersistEntityDataLineDTO();
            componentPersistEntityDataLine.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntityDataLine.setComponentPersistEntityList(componentPersistEntityList);
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

}
