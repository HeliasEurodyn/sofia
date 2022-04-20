package com.crm.sofia.model.common;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
@EqualsAndHashCode
public abstract class BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")

  private String id;

  @CreatedDate
  @Column(name = "created_on", updatable = false, nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant createdOn;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "modified_on", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant modifiedOn;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Column(name = "short_order")
  private Long shortOrder;

}
