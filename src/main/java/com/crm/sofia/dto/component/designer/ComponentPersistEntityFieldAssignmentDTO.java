package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({   "createdOn",
        "createdBy",
        "shortOrder",
        "version","required","onSaveValue"})
@Accessors(chain = true)
public class ComponentPersistEntityFieldAssignmentDTO extends BaseDTO  {

    private String entityType;

    private String entityId;

    private String fieldId;

    private String editor;

    private String defaultValue;

    private String onSaveValue;

    private Boolean required;

    private String type;

}
