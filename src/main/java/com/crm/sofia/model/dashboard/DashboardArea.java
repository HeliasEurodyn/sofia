package com.crm.sofia.model.dashboard;

import com.crm.sofia.dto.dashboard.DashboardItemDTO;
import com.crm.sofia.model.common.BaseEntity;
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
@Entity(name = "DashboardArea")
@Table(name = "dashboard_area")
public class DashboardArea extends BaseEntity {

    @Column
    private String cssclass;

    @Column
    private String cssStyle;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "dashboard_area_id")
    private List<DashboardItem> dashboardItemList;


}
