package com.crm.sofia.model.persistEntity;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type")
public class PersistEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "entity_type", insertable = false, updatable = false)
    private String entitytype;

    @Column(columnDefinition = "TEXT")
    private String query;

}
