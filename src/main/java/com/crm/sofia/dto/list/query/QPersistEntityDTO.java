package com.crm.sofia.dto.list.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class QPersistEntityDTO implements Serializable {

    private String id;

    private String name;

    private String entitytype;

    private String query;

}
