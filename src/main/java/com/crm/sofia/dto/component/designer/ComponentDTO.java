package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.access_control.AccessControlDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Stream;

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

    public Stream<ComponentPersistEntityDTO> flatComponentPersistEntityTree(){
        return this.getComponentPersistEntityList().stream()
                .flatMap(ComponentPersistEntityDTO::streamTree);
    }
}
