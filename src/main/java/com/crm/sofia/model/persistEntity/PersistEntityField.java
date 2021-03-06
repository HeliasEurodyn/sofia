package com.crm.sofia.model.persistEntity;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
//@DynamicUpdate
//@DynamicInsert
@Entity
@Table(name = "persist_entity_field")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "entity_type")
//@DiscriminatorValue("ViewField")
//@DiscriminatorValue("AppViewField"
//@DiscriminatorValue("TableField")
public class PersistEntityField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

    @Column(name = "entity_type" , insertable = true, updatable = false, nullable = false)
    private String entitytype;

    @Column
    private Boolean autoIncrement;

    @Column
    private Boolean primaryKey;

    @Column
    private Boolean hasDefault;

    @Column
    private String defaultValue;

    @Column
    private Boolean isUnsigned;

    @Column
    private Boolean hasNotNull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persist_entity_id")
    private PersistEntity persistEntity;


}
