package com.crm.sofia.model.sofia.component;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.access_control.AccessControl;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "Component")
@Table(name = "component")
public class Component extends MainEntity implements Serializable {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean accessControlEnabled;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_id")
    private List<ComponentPersistEntity> componentPersistEntityList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_id")
    private List<AccessControl> accessControls;

}
