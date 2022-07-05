package com.crm.sofia.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
@EqualsAndHashCode
public class BaseEntity {

    @Id
    @Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    @PreUpdate
    void setIdIfMissing() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
