package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.model.component.ComponentTable;
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

    private List<ComponentTableDTO> componentTableList;

 //   private List<ComponentFieldDTO> componentFieldList;

//    private TableDTO table;

}
