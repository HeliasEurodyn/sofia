package com.crm.sofia.dto.list.query;

import com.crm.sofia.dto.access_control.AccessControlDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

@Data
public class QComponentDTO  implements Serializable {
    private String id;

    private String name;


    private List<QComponentPersistEntityDTO> componentPersistEntityList;

    private Boolean accessControlEnabled;

    private List<AccessControlDTO> accessControls;

    public Stream<QComponentPersistEntityDTO> flatComponentPersistEntityTree(){
        return this.getComponentPersistEntityList().stream()
                .flatMap(QComponentPersistEntityDTO::streamTree);
    }
}
