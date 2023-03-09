package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityDataLineDTO implements Serializable {

    private List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList;

    private List<ComponentPersistEntityDTO> componentPersistEntityList = new ArrayList<>();

    public Stream<ComponentPersistEntityDTO> flatSingleLineCPETree(){

        return this.componentPersistEntityList.stream()
                        .filter(cpe -> !(cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()))
                        .flatMap(ComponentPersistEntityDTO::flatSingleLineCPETree);
    }

}


