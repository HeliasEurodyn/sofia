package com.crm.sofia.model.persistEntity;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "persist_entity")
public class PersistEntity extends MainEntity implements Serializable {

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
    @JoinColumn(name = "persist_entity_id", referencedColumnName = "id")
    private List<PersistEntityField> persistEntityFieldList;

}
