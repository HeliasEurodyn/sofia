package com.crm.sofia.model.sofia.component;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity(name = "ComponentPersistEntity")
@Table(name = "component_persist_entity")
public class ComponentPersistEntity extends BaseEntity implements Serializable {
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.sofia.persistEntity.PersistEntity.class)
    @JoinColumn(name = "persist_entity_id", referencedColumnName = "id")
    private PersistEntity persistEntity;

    @Column
    private String code;

    @Column
    private String selector;

    @Column
    Boolean allowRetrieve;

    @Column
    Boolean allowSave;

    @Column
    private String deleteType;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_persist_entity_id")
    private List<ComponentPersistEntityField> componentPersistEntityFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "parent_id")
    private List<ComponentPersistEntity> componentPersistEntityList = new ArrayList<>();

    private Boolean multiDataLine;

    @Column(name = "short_order")
    private Long shortOrder;
}
