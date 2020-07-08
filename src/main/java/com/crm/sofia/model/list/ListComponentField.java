package com.crm.sofia.model.list;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity(name = "ListComponentColumn")
@Table(name = "list_component_column")
@Accessors(chain = true)
@DynamicUpdate
public class ListComponentField extends BaseEntity {

    @Column
    private String editor;

    @Column
    private String description;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ComponentPersistEntity.class)
    @JoinColumn(name = "component_table_id", referencedColumnName = "id")
    private ComponentPersistEntity componentPersistEntity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ComponentPersistEntityField.class)
    @JoinColumn(name = "component_table_field_id", referencedColumnName = "id")
    private ComponentPersistEntityField componentPersistEntityField;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

}
