package com.crm.sofia.model.menu;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.CustomComponentField;
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
@Entity(name = "MenuComponent")
@Table(name = "menu_component")
@Accessors(chain = true)
@DynamicUpdate
public class MenuComponent extends BaseEntity {

    @Column
    private String name;

    @Column
    private Integer linecounter;

    @OneToMany(
            mappedBy = "menuComponent",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE,CascadeType.REMOVE }
    )
    private List<MenuItemComponent> menuFieldList;


}
