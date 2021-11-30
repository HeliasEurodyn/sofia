package com.crm.sofia.dto.sofia.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.access_control.AccessControlDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

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

    private Boolean accessControlEnabled;

    private List<AccessControlDTO> accessControls;

}
