package com.crm.sofia.model.sofia.persistEntity;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
//@DynamicUpdate
//@DynamicInsert
@Entity
@Table(name = "persist_entity")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "entity_type")
//@DiscriminatorValue("View")
//@DiscriminatorValue("AppView")
//@DiscriminatorValue("Table")
public class PersistEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "entity_type", insertable = true, updatable = false, nullable = false )
    private String entitytype;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private Integer creationVersion;

    @Column
    private String indexes;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            targetEntity=PersistEntityField.class
    )
    @JoinColumn(name = "persist_entity_id", referencedColumnName = "id", nullable = true)
    private List<PersistEntityField> persistEntityFieldList;

}
