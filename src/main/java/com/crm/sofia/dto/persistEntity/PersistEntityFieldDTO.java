package com.crm.sofia.dto.persistEntity;

import com.crm.sofia.dto.common.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY, property = "entitytype") @JsonSubTypes({
//        @JsonSubTypes.Type(value = TableFieldDTO.class, name = "TableField"),
//        @JsonSubTypes.Type(value = ViewFieldDTO.class, name = "ViewField"),
//        @JsonSubTypes.Type(value = AppViewFieldDTO.class, name = "AppViewField")
//})
public class PersistEntityFieldDTO extends BaseDTO {

    private String name;

    private String description;

    private String type;

    private Integer size;

    private String entitytype;

    private Boolean autoIncrement;

    private Boolean primaryKey;

    private Boolean hasDefault;

    private String defaultValue;

    private Boolean isUnsigned;

    private Boolean hasNotNull;

}
