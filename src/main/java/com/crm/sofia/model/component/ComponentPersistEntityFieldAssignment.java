package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ComponentPersistEntityFieldAssignment")
@Table(name = "component_persist_entity_field_assignment")
// @Table(name = "component_persist_entity_field_assignment", uniqueConstraints = @UniqueConstraint(columnNames = {"field_id", "form_id"}))
public class ComponentPersistEntityFieldAssignment extends BaseEntity {

    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "form_id")
    private Long formId;

    @Column
    private String description;

    @Column
    private String editor;

    @Column
    private String defaultValue;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

    @Column
    private Boolean required;

    @Column
    private Integer decimals;

    @Column
    private String fieldtype;

    @Column
    private String css;

    @Column
    private String fieldValue;

    @Column
    private String type;

}
