package com.crm.sofia.dto.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
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
public class FormComponentFieldDTO extends BaseDTO {
    private String editor;
    private String description;
    private Boolean visible;
    private Boolean editable;
    private String defaultValue;
    private Integer decimals;
    private String fieldtype;
    private String cssclass;
    private Object fieldValue;
    private String type;
    private ComponentPersistEntityDTO componentPersistEntity;
    private ComponentPersistEntityFieldDTO componentPersistEntityField;
}
