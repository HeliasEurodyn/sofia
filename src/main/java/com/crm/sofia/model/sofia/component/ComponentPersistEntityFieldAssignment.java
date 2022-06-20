package com.crm.sofia.model.sofia.component;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ComponentPersistEntityFieldAssignment")
@Table(name = "component_persist_entity_field_assignment")
public class ComponentPersistEntityFieldAssignment extends BaseEntity {

    @Column
    private String entityType;

    @Column(columnDefinition = "VARCHAR(36)")
    private String entityId;

    @Column(columnDefinition = "VARCHAR(36)", name = "field_id")
    private String fieldId;

    @Column
    private String description;

    @Column(columnDefinition = "VARCHAR(10000)")
    private String editor;

    @Column
    private String defaultValue;

    @Column
    private String onSaveValue;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

    @Column
    private Boolean required;

    @Column
    private Integer decimals;

    @Column
    private String css;

    @Column
    private String fieldValue;

    @Column
    private String type;

}
