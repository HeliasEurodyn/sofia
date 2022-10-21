package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.ComponentPersistEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormControlTable")
@Table(name = "form_control_table")
public class FormControlTable extends BaseEntity {

    @Column
    private String description;

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

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_control_table_id")
    private List<FormControlTableControl> formControls;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_control_table_id")
    private List<FormControlTableButtonControl> formControlButtons;

}
