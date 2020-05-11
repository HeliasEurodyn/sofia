package com.crm.sofia.model.menu;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.CustomComponent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "MenuItemComponent")
@Table(name = "menu_item_component")
@Accessors(chain = true)
@DynamicUpdate
public class MenuItemComponent extends BaseEntity {

    @Column
    private String name;

    @Column
    private String icon;

    @Column
    private String command;

    @Column
    private Integer linecounter;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MenuComponent.class)
    @JoinColumn(name = "menu_component_id", referencedColumnName = "id")
    private MenuComponent menuComponent;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    private List<MenuItemComponent> menuFieldList;

}
