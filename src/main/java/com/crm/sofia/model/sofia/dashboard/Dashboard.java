package com.crm.sofia.model.sofia.dashboard;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.access_control.AccessControl;
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
@Entity(name = "Dashboard")
@Table(name = "dashboard")
public class Dashboard extends MainEntity {

    @Column
    private String description;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "dashboard_id")
    private List<DashboardArea> dashboardAreaList;

    @Column
    private Boolean accessControlEnabled;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "dashboard_id")
    private List<AccessControl> accessControls;
}
