package com.crm.sofia.dto.sofia.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityFieldAssignmentDTO extends BaseDTO {

    private String entityType;

    private String entityId;

    private String fieldId;

  //  private Long formId;

    private String description;

    private String editor;

    private String defaultValue;

    private Boolean visible;

    private Boolean editable;

    private Boolean required;

    private Integer decimals;

  //  private String fieldtype;

    private String css;

    private Object value;

    private String type;

}
