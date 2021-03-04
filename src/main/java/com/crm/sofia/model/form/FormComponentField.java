package com.crm.sofia.model.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormComponentField")
@Table(name = "form_component_field")
public class FormComponentField extends BaseEntity {

    @Column
    private String editor;

    @Column
    private String description;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

    @Column
    private Boolean required;

    @Column
    private String defaultValue;

    @Column
    private Integer decimals;

    @Column
    private String fieldtype;

    @Column
    private String cssclass;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = ComponentPersistEntity.class)
    @JoinColumn(name = "component_persist_entity_id", referencedColumnName = "id")
    private ComponentPersistEntity componentPersistEntity;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = ComponentPersistEntityField.class)
    @JoinColumn(name = "component_persist_entity_field_id", referencedColumnName = "id")
    private ComponentPersistEntityField componentPersistEntityField;
}
