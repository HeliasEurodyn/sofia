package com.crm.sofia.dto.list.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class QListComponentFieldDTO implements Serializable {

    private String id;

    private String code;
    private String editor;
    private String type;

    private QComponentPersistEntityDTO componentPersistEntity;
    private QComponentPersistEntityFieldDTO componentPersistEntityField;
    private Boolean visible;
    private Boolean editable;
    private Boolean headerEditable;
    private String editableRelFieldCode;
    private Boolean headerFilter;
    private Boolean required;
    private String defaultValue;

    private String fieldtype;
    private String operator;

    private Object fieldValue;
    private String formulaType;

}
