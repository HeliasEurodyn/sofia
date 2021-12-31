package com.crm.sofia.dto.sofia.component.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class ComponentPersistEntityDataLineUiDTO implements Serializable {

    private List<ComponentPersistEntityDataLineFieldUiDTO> componentPersistEntityFieldList;

    private List<ComponentPersistEntityUiDTO> componentPersistEntityList = new ArrayList<>();

}


