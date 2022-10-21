package com.crm.sofia.dto.persistEntity;

import com.crm.sofia.dto.common.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY, property = "entitytype")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = TableDTO.class, name = "Table"),
//        @JsonSubTypes.Type(value = ViewDTO.class, name = "View"),
//        @JsonSubTypes.Type(value = AppViewDTO.class, name = "AppView")
//})
public class PersistEntityDTO extends BaseDTO {

    private String name;

    private String description;

    private String entitytype;

    private String query;

    private Integer creationVersion;

    private String indexes;

    private List<PersistEntityFieldDTO> persistEntityFieldList;

}


