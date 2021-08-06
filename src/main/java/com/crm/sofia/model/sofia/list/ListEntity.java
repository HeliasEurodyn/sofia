package com.crm.sofia.model.sofia.list;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.component.Component;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListEntity")
@Table(name = "list")
public class ListEntity extends BaseEntity {


    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String icon;

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


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.sofia.component.Component.class)
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

    @Column
    private String instanceVersion;

    @Column
    private String jsonUrl;
}
