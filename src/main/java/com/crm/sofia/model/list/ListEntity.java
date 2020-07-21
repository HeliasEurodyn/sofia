package com.crm.sofia.model.list;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListEntity")
@Table(name = "list")
public class ListEntity extends BaseEntity {

    @Column
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private java.util.List<ListComponent> listComponentList;

}
