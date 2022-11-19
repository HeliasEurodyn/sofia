package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityDTO extends BaseDTO {

    private String code;

    private String selector;

    Boolean allowRetrieve;

    Boolean allowSave;

    private String deleteType;

    private PersistEntityDTO persistEntity;

    private List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList;

    private List<ComponentPersistEntityFieldDTO> defaultComponentPersistEntityFieldList;

    /* Form multiline */
    private Boolean multiDataLine;

    private List<ComponentPersistEntityDataLineDTO> componentPersistEntityDataLines = new ArrayList<>();

    /* Children */
    private List<ComponentPersistEntityDTO> componentPersistEntityList = new ArrayList<>();

    private List<ComponentPersistEntityDTO> defaultComponentPersistEntityList = new ArrayList<>();

    public Stream<ComponentPersistEntityDTO> streamTree(){
        return Stream.concat(Stream.of(this), this.getComponentPersistEntityList().stream().flatMap(ComponentPersistEntityDTO::streamTree));
    }
}
