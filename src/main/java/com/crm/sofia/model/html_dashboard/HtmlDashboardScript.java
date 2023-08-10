package com.crm.sofia.model.html_dashboard;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity(name = "HtmlDashboardScript")
@Table(name = "html_dashboard_script")
public class HtmlDashboardScript  extends BaseEntity implements Serializable {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String script;

    @Column(name = "short_order")
    private Long shortOrder;
}
