package com.crm.sofia.dto.list.query;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class QComponentPersistEntityDTO implements Serializable {

    Boolean allowRetrieve;

    private String id;

    private String code;

    private QPersistEntityDTO persistEntity;

    private List<QComponentPersistEntityDTO> componentPersistEntityList = new ArrayList<>();

    public Stream<QComponentPersistEntityDTO> streamTree(){
        return Stream.concat(Stream.of(this), this.getComponentPersistEntityList().stream().flatMap(QComponentPersistEntityDTO::streamTree));
    }
}
