package com.crm.sofia.dto.sofia.list.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.sofia.component.user.ComponentPersistEntityFieldUiDTO;
import com.crm.sofia.dto.sofia.component.user.ComponentPersistEntityUiDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

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
public class ListComponentFieldUiDTO extends BaseDTO {
    private String code;
    private String editor;
    private String description;
    private String type;
    private ComponentPersistEntityUiDTO componentPersistEntity;
    private ComponentPersistEntityFieldUiDTO componentPersistEntityField;
    private Boolean visible;
    private Boolean editable;
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
