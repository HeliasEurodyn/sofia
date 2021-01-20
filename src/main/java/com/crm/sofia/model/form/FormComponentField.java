package com.crm.sofia.model.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormComponentField")
@Table(name = "formcomponentfield")
public class FormComponentField extends BaseEntity {
    private String editor;
    private String description;
    private Boolean visible;
    private Boolean editable;
    private String defaultValue;
    private Integer decimals;
    private String fieldtype;
    private String cssclass;
    private Object fieldValue;
    private String type;
    private ComponentPersistEntityDTO componentPersistEntity;
    private ComponentPersistEntityFieldDTO componentPersistEntityField;
}
