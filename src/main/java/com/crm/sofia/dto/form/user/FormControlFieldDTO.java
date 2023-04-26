package com.crm.sofia.dto.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.form.user.translation.FormControlFieldTranslationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version","componentPersistEntity","componentPersistEntityField"})
@Accessors(chain = true)
public class FormControlFieldDTO extends BaseDTO {
    private String editor;
    private String description;
    private String message;
    private String placeholder;
    private Boolean visible;
    private Boolean editable;
    private Boolean required;
    private Boolean headerFilter;
    private String css;
    private ComponentPersistEntityDTO componentPersistEntity;
    private ComponentPersistEntityFieldDTO componentPersistEntityField;
    private String fieldId;
    private String mask;
//    private List<FormControlFieldTranslationDTO> translations;
}
