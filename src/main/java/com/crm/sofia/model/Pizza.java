package com.crm.sofia.model;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.BaseNoIdEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;



@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pizza extends BaseNoIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public abstract void tastesLike();
}