package com.crm.sofia.dto.list.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class QComponentPersistEntityDTO implements Serializable {

    Boolean allowRetrieve;

    private String id;

    private String code;

    private QPersistEntityDTO persistEntity;

}
