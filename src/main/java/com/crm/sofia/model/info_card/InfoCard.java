package com.crm.sofia.model.info_card;

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
@Entity(name = "InfoCard")
@Table(name = "info_card")
public class InfoCard extends BaseEntity {

    @Column
    private String title;

    @Column
    private String icon;

    @Column
    private String description;

    @Column
    private String cardText;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private String command;

    @Column
    private String commandIcon;

    @Column
    private Boolean executePeriodically;

    @Column
    private Integer executionInterval;

}
