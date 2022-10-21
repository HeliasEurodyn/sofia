package com.crm.sofia.dto.component.user;

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
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class ComponentPersistEntityDataLineFieldUiDTO implements Serializable {

    private String id;

    private String code;

    private String defaultValue;

    private Object value;

  //  ComponentPersistEntityFieldAssignmentDTO assignment;
}
