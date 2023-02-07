package com.crm.sofia.mapper.form.user;

import com.crm.sofia.dto.form.user.FormUiControlDTO;
import com.crm.sofia.dto.form.user.FormUiDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.form.FormArea;
import com.crm.sofia.model.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormUiMapper extends BaseMapper<FormUiDTO, FormEntity> {

    public FormUiDTO mapForm(FormEntity entity, String languageId) {


        /*
         * Do Translations on Entity
         */
        if (!(languageId == null ? "" : languageId).equals("") && ((entity.getTranslations() == null ? 0 : entity.getTranslations().size()) > 0)) {

            entity.getTranslations().stream().filter(translation -> translation.getLanguage().getId().equals(languageId)).forEach(translation -> {
                entity.setName(translation.getName());
                entity.setTitle(translation.getTitle());
                entity.setDescription(translation.getDescription());
            });

            entity.getFormTabs().forEach(tab -> {
                        tab.getTranslations().stream()
                                .filter(translation -> translation.getLanguage().getId().equals(languageId))
                                .forEach(translation -> tab.setDescription(translation.getDescription()));
                    });

            entity.getFormPopups().forEach(popup -> {
                popup.getTranslations().stream()
                        .filter(translation -> translation.getLanguage().getId().equals(languageId))
                        .forEach(translation -> popup.setDescription(translation.getDescription()));
            });

            List<FormArea> tabsAreas  = entity.getFormTabs().stream()
                    .flatMap(tab -> tab.getFormAreas().stream())
                    .collect(Collectors.toList());

            List<FormArea> popupsAreas  = entity.getFormPopups().stream()
                    .flatMap(popup -> popup.getFormAreas().stream())
                    .collect(Collectors.toList());


            Stream.concat(tabsAreas.stream(), popupsAreas.stream()).forEach(area -> {

                    area.getTranslations().stream()
                            .filter(translation -> translation.getLanguage().getId().equals(languageId))
                            .forEach(translation -> {
                        area.setTitle(translation.getTitle());
                        area.setDescription(translation.getDescription());
                    });

                    area.getFormControls().stream()
                            .filter(control -> control.getType().equals("field"))
                            .forEach(control -> {
                                control.getFormControlField().getTranslations().stream()
                                        .filter(translation -> translation.getLanguage().getId().equals(languageId))
                                        .forEach(translation -> {
                                            control.getFormControlField().setDescription(translation.getDescription());
                                            control.getFormControlField().setMessage(translation.getMessage());
                                            control.getFormControlField().setPlaceholder(translation.getPlaceholder());
                                        });
                            });

                    area.getFormControls().stream()
                            .filter(control -> control.getType().equals("button"))
                            .forEach(control -> {
                                control.getFormControlButton().getTranslations().stream()
                                        .filter(translation -> translation.getLanguage().getId().equals(languageId))
                                        .forEach(translation -> control.getFormControlButton().setDescription(translation.getDescription()));
                            });
                });

        }


        FormUiDTO dto = this.map(entity);

        dto.getFormTabs().forEach(tab -> {
            tab.getFormAreas().forEach(area -> {
                area.getFormControls().forEach(control -> {
                    this.setFieldIdToFormControl(control);
                });
            });
        });

        dto.getFormPopups().forEach(popup -> {
            popup.getFormAreas().forEach(area -> {
                area.getFormControls().forEach(control -> {
                    this.setFieldIdToFormControl(control);
                });
            });
        });

        return dto;
    }

    public void setFieldIdToFormControl(FormUiControlDTO control) {
        if (control.getType().equals("field")) {
            String fieldId = control.getFormControlField().getComponentPersistEntityField().getId();
            control.getFormControlField().setFieldId(fieldId);
            control.getFormControlField().setComponentPersistEntity(null);
            control.getFormControlField().setComponentPersistEntityField(null);
        } else if (control.getType().equals("table")) {
            control.getFormControlTable().getFormControls().forEach(tableConrtol -> {
                if (tableConrtol.getType().equals("field")) {
                    String fieldId = tableConrtol.getFormControlField().getComponentPersistEntityField().getId();
                    tableConrtol.getFormControlField().setFieldId(fieldId);
                    tableConrtol.getFormControlField().setComponentPersistEntity(null);
                    tableConrtol.getFormControlField().setComponentPersistEntityField(null);
                }
            });
        }
    }

}
