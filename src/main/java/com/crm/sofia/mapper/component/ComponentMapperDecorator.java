package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.appview.AppViewFieldDTO;
import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.view.ViewFieldDTO;
import com.crm.sofia.mapper.appview.AppViewFieldMapper;
import com.crm.sofia.mapper.appview.AppViewMapper;
import com.crm.sofia.mapper.table.TableFieldMapper;
import com.crm.sofia.mapper.table.TableMapper;
import com.crm.sofia.mapper.view.ViewFieldMapper;
import com.crm.sofia.mapper.view.ViewMapper;
import com.crm.sofia.model.appview.AppView;
import com.crm.sofia.model.appview.AppViewField;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.crm.sofia.model.table.Table;
import com.crm.sofia.model.table.TableField;
import com.crm.sofia.model.view.View;
import com.crm.sofia.model.view.ViewField;
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

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private AppViewMapper appViewMapper;

    @Autowired
    private TableFieldMapper tableFieldMapper;

    @Autowired
    private ViewFieldMapper viewFieldMapper;

    @Autowired
    private AppViewFieldMapper appViewFieldMapper;

    @Override
    public Component mapWithPersistEntities(ComponentDTO componentDTO) {

        Component component = this.componentMapper.map(componentDTO);
        List<ComponentPersistEntity> componentPersistEntityList = new ArrayList<>();

        for (ComponentPersistEntityDTO componentPersistEntityDTO : componentDTO.getComponentPersistEntityList()) {
            ComponentPersistEntity componentPersistEntity = this.componentPersistEntityMapper.map(componentPersistEntityDTO);
            PersistEntityDTO persistEntityDTO = componentPersistEntityDTO.getPersistEntity();

            if (persistEntityDTO instanceof TableDTO) {
                Table table = this.tableMapper.map((TableDTO) persistEntityDTO);
                componentPersistEntity.setPersistEntity(table);
            } else if (persistEntityDTO instanceof ViewDTO) {
                View view = this.viewMapper.map((ViewDTO) persistEntityDTO);
                componentPersistEntity.setPersistEntity(view);
            } else {
                AppView appView = this.appViewMapper.map((AppViewDTO) persistEntityDTO);
                componentPersistEntity.setPersistEntity(appView);
            }

            List<ComponentPersistEntityField> componentPersistEntityFieldList = new ArrayList<>();
            for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityDTO.getComponentPersistEntityFieldList()) {
                ComponentPersistEntityField componentPersistEntityField = this.componentPersistEntityFieldMapper.map(componentPersistEntityFieldDTO);
                PersistEntityFieldDTO persistEntityFieldDTO = componentPersistEntityFieldDTO.getPersistEntityField();

                if (persistEntityFieldDTO instanceof TableFieldDTO) {
                    TableField tableField = this.tableFieldMapper.map((TableFieldDTO) persistEntityFieldDTO);
                    componentPersistEntityField.setPersistEntityField(tableField);
                } else if (persistEntityFieldDTO instanceof ViewFieldDTO) {
                    ViewField viewField = this.viewFieldMapper.map((ViewFieldDTO) persistEntityFieldDTO);
                    componentPersistEntityField.setPersistEntityField(viewField);
                } else {
                    AppViewField appViewField = this.appViewFieldMapper.map((AppViewFieldDTO) persistEntityFieldDTO);
                    componentPersistEntityField.setPersistEntityField(appViewField);
                }
                componentPersistEntityFieldList.add(componentPersistEntityField);
            }
            componentPersistEntity.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntityList.add(componentPersistEntity);
        }

        component.setComponentPersistEntityList(componentPersistEntityList);
        return component;
    }


}
