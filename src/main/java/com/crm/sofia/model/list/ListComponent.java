package com.crm.sofia.model.list;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.Component;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "ListComponent")
@Table(name = "list_component")
@Accessors(chain = true)
@DynamicUpdate
public class ListComponent extends BaseEntity {


    @Column
    private String code;

    @Column
    private String selector;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.component.Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;

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

}
