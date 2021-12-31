package com.crm.sofia.dto.sofia.component.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class ComponentPersistEntityUiDTO implements Serializable {

    private Long id;

    private String code;

    private List<ComponentPersistEntityFieldUiDTO> componentPersistEntityFieldList;

    private List<ComponentPersistEntityFieldUiDTO> defaultComponentPersistEntityFieldList;

    /* Form multiline */
    private Boolean multiDataLine;

    private List<ComponentPersistEntityDataLineUiDTO> componentPersistEntityDataLines = new ArrayList<>();

    /* Children */
    @JsonManagedReference
    private List<ComponentPersistEntityUiDTO> componentPersistEntityList = new ArrayList<>();

    @JsonManagedReference
    private List<ComponentPersistEntityUiDTO> defaultComponentPersistEntityList = new ArrayList<>();
}
