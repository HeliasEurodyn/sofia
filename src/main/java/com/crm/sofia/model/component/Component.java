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
@Entity(name = "Component")
@Table(name = "component")
@Accessors(chain = true)
@DynamicUpdate
public class Component extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_id")
    private List<ComponentPersistEntity> componentPersistEntityList;


}
