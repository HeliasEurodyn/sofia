package com.crm.sofia.model.sofia.custom_query;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "CustomQuery")
@Table(name = "custom_query")
public class CustomQuery extends BaseEntity {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String query;


}
