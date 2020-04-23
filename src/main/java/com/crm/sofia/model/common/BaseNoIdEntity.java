package com.crm.sofia.model.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
public abstract class BaseNoIdEntity {

  @CreatedDate
  @Column(name = "created_on", updatable = false, nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant createdOn;

  @CreatedBy
  //@Column(name = "created_by", updatable = false, nullable = false)
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "modified_on", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant modifiedOn;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Version
  private Long version;
}
