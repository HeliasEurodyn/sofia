package com.crm.sofia.model.persistEntity;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
@EqualsAndHashCode(callSuper = true)
public abstract class PersistEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer creationVersion;


}
