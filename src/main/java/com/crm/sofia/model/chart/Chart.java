package com.crm.sofia.model.chart;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "Chart")
@Table(name = "chart")
public class Chart extends BaseEntity {

    @Column
    private String title;

    @Column
    private String icon;

    @Column
    private String secondTitle;

    @Column(columnDefinition = "TEXT")
    private String chartJson;

    @Column(columnDefinition = "TEXT")
    private String optionsJson;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private String horizontalAxe;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "chart_id")
    private List<ChartField> chartFieldList;

    @Column
    private Boolean executePeriodically;

    @Column
    private Boolean refreshButton;

    @Column
    private Integer executionInterval;

}
