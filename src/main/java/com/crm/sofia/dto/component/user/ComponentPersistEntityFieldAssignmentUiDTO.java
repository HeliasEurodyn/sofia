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
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityFieldAssignmentUiDTO implements Serializable {

    private String id;

    private String entityType;

    private String entityId;

    private String fieldId;

    private String editor;

    private String filterEditor;

    private String defaultValue;

    private String type;

}
