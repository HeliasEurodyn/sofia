package com.crm.sofia.model.sofia.dashboard;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "DashboardItem")
@Table(name = "dashboard_item")
public class DashboardItem extends BaseEntity {

    @Column
    private String type;

    @Column
    private String entityId;

    @Column
    private String cssclass;

    @Column(name = "short_order")
    private Long shortOrder;
}
