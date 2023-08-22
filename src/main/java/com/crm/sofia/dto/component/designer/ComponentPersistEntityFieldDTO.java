package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
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
@JsonIgnoreProperties(
        { "createdOn",
                "createdBy",
                "shortOrder",
                "version",
                "locateStatement",
        "saveStatement",
        "persistEntityField",
        "assignment"})
@Accessors(chain = true)
public class ComponentPersistEntityFieldDTO extends BaseDTO {

    private String code;

    private String defaultValue;

    private Object value;

    private String saveStatement;

    private String locateStatement;

    private PersistEntityFieldDTO persistEntityField;

    ComponentPersistEntityFieldAssignmentDTO assignment;

}
