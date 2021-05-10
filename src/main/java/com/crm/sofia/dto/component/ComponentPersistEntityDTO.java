package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityDTO extends BaseDTO {

    private PersistEntityDTO persistEntity;

    private String code;

    private String selector;

    Boolean allowRetrieve;

    Boolean allowSave;

    private String deleteType;

    private List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList;

    /* Form multiline */
    private List<ComponentPersistEntityFieldDTO> defaultComponentPersistEntityFieldList;

    private Boolean multiDataLine;

    private List<ComponentPersistEntityDataLineDTO> componentPersistEntityDataLines = new ArrayList<>();

    private List<ComponentPersistEntityDTO> componentPersistEntityList = new ArrayList<>();

    private List<ComponentPersistEntityDTO> defaultComponentPersistEntityList = new ArrayList<>();

}
