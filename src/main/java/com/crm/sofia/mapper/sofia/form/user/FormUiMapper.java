package com.crm.sofia.mapper.sofia.form.user;

import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormUiMapper extends BaseMapper<FormUiDTO, FormEntity> {

    public FormUiDTO mapForm(FormEntity entity) {
        FormUiDTO dto = this.map(entity);
        dto.getFormTabs().forEach(tab -> {
            tab.getFormAreas().forEach(area -> {
                area.getFormControls().forEach(control -> {
                    if (control.getType().equals("field")) {
                        Long fieldId = control.getFormControlField().getComponentPersistEntityField().getId();
                        control.getFormControlField().setFieldId(fieldId);
                        control.getFormControlField().setComponentPersistEntity(null);
                        control.getFormControlField().setComponentPersistEntityField(null);
                    } else if (control.getType().equals("table")){
                        control.getFormControlTable().getFormControls().forEach(tableConrtol -> {
                            if (tableConrtol.getType().equals("field")) {
                                Long fieldId = tableConrtol.getFormControlField().getComponentPersistEntityField().getId();
                                tableConrtol.getFormControlField().setFieldId(fieldId);
                                tableConrtol.getFormControlField().setComponentPersistEntity(null);
                                tableConrtol.getFormControlField().setComponentPersistEntityField(null);
                            }
                        });
                    }
                });
            });
        });

        dto.getFormPopups().forEach(popup -> {
            popup.getFormAreas().forEach(area -> {
                area.getFormControls().forEach(control -> {
                    if (control.getType().equals("field")) {
                        Long fieldId = control.getFormControlField().getComponentPersistEntityField().getId();
                        control.getFormControlField().setFieldId(fieldId);
                        control.getFormControlField().setComponentPersistEntity(null);
                        control.getFormControlField().setComponentPersistEntityField(null);
                    } else if (control.getType().equals("table")){
                        control.getFormControlTable().getFormControls().forEach(tableConrtol -> {
                            if (tableConrtol.getType().equals("field")) {
                                Long fieldId = tableConrtol.getFormControlField().getComponentPersistEntityField().getId();
                                tableConrtol.getFormControlField().setFieldId(fieldId);
                                tableConrtol.getFormControlField().setComponentPersistEntity(null);
                                tableConrtol.getFormControlField().setComponentPersistEntityField(null);
                            }
                        });
                    }
                });
            });
        });

        return dto;
    }

}
