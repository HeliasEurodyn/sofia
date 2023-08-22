package com.crm.sofia.model.form;

import com.crm.sofia.model.access_control.AccessControl;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.form.translation.FormTranslation;
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
@Entity(name = "FormEntity")
@Table(name = "form")
public class FormEntity extends MainEntity {

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String icon;

    @Column
    private Boolean accessControlEnabled;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormTab> formTabs;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormPopup> formPopups;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormScript> formScripts;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormCss> formCssList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String script;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String scriptMin;

    @Column(columnDefinition ="BIGINT(20) default 0")
    private Long instanceVersion;

    @Column
    private String jsonUrl;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormActionButton> formActionButtons;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<AccessControl> accessControls;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_id")
    private List<FormTranslation> translations;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "form_id")
    private List<FormBackendActions> formBackendActionsList;
}
