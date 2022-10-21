package com.crm.sofia.model.list;

import com.crm.sofia.model.access_control.AccessControl;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.list.translation.ListTranslation;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListEntity")
@Table(name = "list")
public class ListEntity extends MainEntity implements Serializable {

    public static final String HASH_KEY = "listEntity";

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String headerTitle;

    @Column(length=1024)
    private String HeaderDescription;

    @Column
    private String headerIcon;

    @Column
    private String title;

    @Column(length=1024)
    private String description;

    @Column
    private String icon;

    @Column
    private String groupingTitle;

    @Column(length=1024)
    private String groupingDescription;

    @Column
    private String selector;

    @Column(columnDefinition = "TEXT")
    private String filterFieldStructure;

    @Column
    private Boolean customFilterFieldStructure;

    @Column
    private Boolean exportExcel;

    @Column
    private String defaultPage;

    @Column
    private Boolean autoRun;

    @Column
    private Boolean listVisible;

    @Column
    private Boolean filterVisible;

    @Column
    private Boolean hasPagination;

    @Column
    private Long pageSize;

    @Column
    private Long totalPages;

    @Column
    private Long currentPage;

    @Column
    private Long totalRows;

    @Column
    private Boolean hasMaxSize;

    @Column
    private Long maxSize;

    @Column
    private Boolean HeaderFilters;

    @Column
    private String rowNavigation;

    @Column
    private Boolean accessControlEnabled;

    @Column
    private String businessUnit;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private List<ListActionButton> listActionButtons;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "column_list_component_id")
    private List<ListComponentField> listComponentColumnFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "filter_list_component_id")
    private List<ListComponentField> listComponentFilterFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "side_group_list_component_id")
    private List<ListComponentField> listComponentLeftGroupFieldList;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "top_group_list_component_id")
    private List<ListComponentField> listComponentTopGroupFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "order_by_list_component_id")
    private List<ListComponentField> listComponentOrderByFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "action_list_component_id")
    private List<ListComponentField> listComponentActionFieldList;

    @Column(columnDefinition ="BIGINT(20) default 0")
    private Long instanceVersion;

    @Column
    private String jsonUrl;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String script;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String scriptMin;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private List<ListScript> listScripts;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private List<ListCss> listCssList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private List<AccessControl> accessControls;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_id")
    private List<ListTranslation> translations;

}
