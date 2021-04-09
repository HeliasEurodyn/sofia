package com.crm.sofia.model.component;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ComponentPersistEntityField")
@javax.persistence.Table(name = "component_persist_entity_field")
public class ComponentPersistEntityField extends BaseEntity {

    @Column
    private String description;

    @Column
    private String editor;

    @Column
    private String defaultValue;

    @Column
    private String saveStatement;

    @Column
    private String locateStatement;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            targetEntity = ComponentPersistEntity.class)
    @JoinColumn(name = "join_persist_entity_id", referencedColumnName = "id")
    private ComponentPersistEntity joinPersistEntity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PersistEntityField.class)
    @JoinColumn(name = "persist_entity_field_id", referencedColumnName = "id")
    private PersistEntityField persistEntityField;

}
