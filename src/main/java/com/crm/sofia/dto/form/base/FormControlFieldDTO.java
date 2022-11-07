package com.crm.sofia.dto.form.base;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class FormControlFieldDTO extends BaseDTO {

    private String editor;
    private String description;
    private String message;
    private String placeholder;
    private Boolean visible;
    private Boolean editable;
    private Boolean required;
    private String css;
    private ComponentPersistEntityDTO componentPersistEntity;
    private ComponentPersistEntityFieldDTO componentPersistEntityField;
    private String mask;
}
