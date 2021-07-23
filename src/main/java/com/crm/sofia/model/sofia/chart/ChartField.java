package com.crm.sofia.model.sofia.chart;

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
@Entity(name = "ChartField")
@Table(name = "chart_field")
public class ChartField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer size;

    @Column(columnDefinition = "TEXT")
    private String chartJson;

    @Column
    private String description;

}
