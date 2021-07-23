package com.crm.sofia.mapper.sofia.component;
import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.sofia.persistEntity.PersistEntityDTO;
import com.crm.sofia.dto.sofia.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.mapper.sofia.persistEntity.PersistEntityFieldMapper;
import com.crm.sofia.mapper.sofia.persistEntity.PersistEntityMapper;
import com.crm.sofia.model.sofia.component.Component;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityField;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.model.sofia.persistEntity.PersistEntityField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponentMapperDecorator extends ComponentMapper {

    @Autowired
    @Qualifier("delegate")
    private ComponentMapper componentMapper;

    @Autowired
    private ComponentPersistEntityMapper componentPersistEntityMapper;

    @Autowired
    private ComponentPersistEntityFieldMapper componentPersistEntityFieldMapper;

//    @Autowired
//    private TableMapper tableMapper;
//
//    @Autowired
//    private ViewMapper viewMapper;
//
//    @Autowired
//    private AppViewMapper appViewMapper;
//
//    @Autowired
//    private TableFieldMapper tableFieldMapper;
//
//    @Autowired
//    private ViewFieldMapper viewFieldMapper;
//
//    @Autowired
//    private AppViewFieldMapper appViewFieldMapper;

    @Autowired
    private PersistEntityMapper persistEntityMapper;

    @Autowired
    private PersistEntityFieldMapper persistEntityFieldMapper;

    @Override
    public Component mapWithPersistEntities(ComponentDTO componentDTO) {

        Component component = this.componentMapper.map(componentDTO);
        List<ComponentPersistEntity> componentPersistEntityList = new ArrayList<>();

        for (ComponentPersistEntityDTO componentPersistEntityDTO : componentDTO.getComponentPersistEntityList()) {
            ComponentPersistEntity componentPersistEntity = this.componentPersistEntityMapper.map(componentPersistEntityDTO);
            PersistEntityDTO persistEntityDTO = componentPersistEntityDTO.getPersistEntity();

            PersistEntity persistEntity = this.persistEntityMapper.map( persistEntityDTO);
            componentPersistEntity.setPersistEntity(persistEntity);

            List<ComponentPersistEntityField> componentPersistEntityFieldList = new ArrayList<>();
            for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityDTO.getComponentPersistEntityFieldList()) {
                ComponentPersistEntityField componentPersistEntityField = this.componentPersistEntityFieldMapper.map(componentPersistEntityFieldDTO);
                PersistEntityFieldDTO persistEntityFieldDTO = componentPersistEntityFieldDTO.getPersistEntityField();

                PersistEntityField persistEntityField = this.persistEntityFieldMapper.map( persistEntityFieldDTO);
                componentPersistEntityField.setPersistEntityField(persistEntityField);

                componentPersistEntityFieldList.add(componentPersistEntityField);
            }
            componentPersistEntity.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntityList.add(componentPersistEntity);
        }

        component.setComponentPersistEntityList(componentPersistEntityList);
        return component;
    }


}
