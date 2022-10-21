package com.crm.sofia.dto.list.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder",
        "version","operator",
        "shortLocation",
        "decimals","componentPersistEntity",
        "componentPersistEntityField" }
)
public class ListComponentSubFieldUiDTO extends BaseDTO implements Serializable {
    private String code;
    private String editor;
    private String description;
    private String type;
    private String mask;
    private Boolean visible;
    private Boolean editable;
    private Boolean headerEditable;
    private String editableRelFieldCode;
    private Boolean headerFilter;
    private Boolean required ;
    private String defaultValue;
    private Integer decimals;
    private String fieldtype;
    private String shortLocation;
    private String operator;
    private String bclass;
    private String css;
    private Object fieldValue;
}
