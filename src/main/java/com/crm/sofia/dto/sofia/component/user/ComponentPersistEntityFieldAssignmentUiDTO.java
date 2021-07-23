package com.crm.sofia.dto.sofia.component.user;

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
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityFieldAssignmentUiDTO {

    private Long id;

    private String entityType;

    private Long entityId;

    private Long fieldId;

    private String editor;

    private String defaultValue;

    private String type;

}
