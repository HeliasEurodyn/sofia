package com.crm.sofia.model.html_dashboard;

import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "HtmlDashboard")
@Table(name = "html_dashboard")
public class HtmlDashboard extends MainEntity {

    @Column
    private String code;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String html;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "html_dashboard_id")
    private List<HtmlDashboardScript> scripts;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String script;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String scriptMin;
}
