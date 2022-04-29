package com.crm.sofia.model.sofia.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormControlField")
@Table(name = "form_control_field")
public class FormControlField extends BaseEntity {

    @Column
    private String editor;

    @Column
    private String description;

    @Column(length=1024)
    private String message;

    @Column
    private String placeholder;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

    @Column
    private Boolean required;

    @Column
    private String css;


    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = ComponentPersistEntity.class)
    @JoinColumn(name = "component_persist_entity_id", referencedColumnName = "id")
    private ComponentPersistEntity componentPersistEntity;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = ComponentPersistEntityField.class)
    @JoinColumn(name = "component_persist_entity_field_id", referencedColumnName = "id")
    private ComponentPersistEntityField componentPersistEntityField;

    @Column
    private String mask;
}
