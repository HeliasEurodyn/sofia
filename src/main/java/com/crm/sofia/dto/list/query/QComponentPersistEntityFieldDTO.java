package com.crm.sofia.dto.list.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class QComponentPersistEntityFieldDTO implements Serializable {

    private String id;

    private String defaultValue;

    private Object value;

    private QPersistEntityFieldDTO persistEntityField;

}
