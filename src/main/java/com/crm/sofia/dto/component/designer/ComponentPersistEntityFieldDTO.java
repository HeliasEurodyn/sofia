package com.crm.sofia.dto.component.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityFieldDTO extends BaseDTO {

    private String description;

    private String editor;

    private String defaultValue;

    private Object value;

    private String saveStatement;

    private String locateStatement;

    private ComponentPersistEntityDTO joinPersistEntity;

    private PersistEntityFieldDTO persistEntityField;

    ComponentPersistEntityFieldAssignmentDTO assignment;



//    private Boolean visible ;
//
//    private Boolean  editable ;
//
//    private Boolean required ;
//
//    private Integer decimals;
//
//    private String fieldtype ;
//
//    private String css ;
//
//    private String type;

}
