package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentDTO extends BaseDTO {

    private String name;

    private String description;

    private List<ComponentPersistEntityDTO> componentPersistEntityList;

 //   private List<ComponentFieldDTO> componentFieldList;

//    private TableDTO table;

}
