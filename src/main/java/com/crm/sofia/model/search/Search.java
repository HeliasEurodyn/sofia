package com.crm.sofia.model.search;

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
@Entity(name = "Search")
@Table(name = "search")
public class Search extends BaseEntity {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String query;

}
