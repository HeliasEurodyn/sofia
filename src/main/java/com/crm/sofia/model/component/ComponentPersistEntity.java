package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.persistEntity.PersistEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "ComponentPersistEntity")
@Table(name = "component_persist_entity")
@Accessors(chain = true)
@DynamicUpdate
public class ComponentPersistEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.persistEntity.PersistEntity.class)
    @JoinColumn(name = "persist_entity_id", referencedColumnName = "id")
    private PersistEntity persistEntity;

    @Column
    private String code;

    @Column
    private String selector;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_persist_entity_id")
    private List<ComponentPersistEntityField> componentPersistEntityFieldList;

}