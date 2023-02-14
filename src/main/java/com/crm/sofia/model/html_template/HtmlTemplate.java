package com.crm.sofia.model.html_template;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.component.Component;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity(name = "HtmlTemplate")
@Table(name = "html_template")

public class HtmlTemplate extends MainEntity {
    @Column
    private String title;

    @Column
    private String description;


    @Column(columnDefinition = "TEXT")
    private String html;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;
}
