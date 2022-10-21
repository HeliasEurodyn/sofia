package com.crm.sofia.model.persistEntity;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "persist_entity_field")
public class PersistEntityField extends BaseEntity implements Serializable {

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

    @Column(name = "short_order")
    private Long shortOrder;

    @Column(length = 2000)
    private String onSaveValue;
}
