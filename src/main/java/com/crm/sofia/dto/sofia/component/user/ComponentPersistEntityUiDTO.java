package com.crm.sofia.dto.sofia.component.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class ComponentPersistEntityUiDTO {

    private Long id;

    private String code;

    private List<ComponentPersistEntityFieldUiDTO> componentPersistEntityFieldList;

    private List<ComponentPersistEntityFieldUiDTO> defaultComponentPersistEntityFieldList;

    /* Form multiline */
    private Boolean multiDataLine;

    private List<ComponentPersistEntityDataLineUiDTO> componentPersistEntityDataLines = new ArrayList<>();

    /* Children */
    private List<ComponentPersistEntityUiDTO> componentPersistEntityList = new ArrayList<>();

    private List<ComponentPersistEntityUiDTO> defaultComponentPersistEntityList = new ArrayList<>();
}
