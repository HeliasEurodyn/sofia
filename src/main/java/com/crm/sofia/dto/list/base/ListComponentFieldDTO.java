package com.crm.sofia.dto.list.base;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.list.base.translation.ListComponentFieldTranslationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder",
        "version","operator",
        "shortLocation",
        "decimals","componentPersistEntity",
        "componentPersistEntityField" })
public class ListComponentFieldDTO extends BaseDTO {
    private String code;
    private String editor;
    private String description;
    private String type;
    private String mask;
    private ComponentPersistEntityDTO componentPersistEntity;
    private ComponentPersistEntityFieldDTO componentPersistEntityField;
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
    private String formulaType;
    private List<ListComponentSubFieldDTO> listComponentActionFieldList;
    private List<ListComponentFieldTranslationDTO> translations;
}
