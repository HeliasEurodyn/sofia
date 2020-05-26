package com.crm.sofia.model.menu;

import com.crm.sofia.model.common.BaseEntity;
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
@Entity(name = "MenuField")
@Table(name = "menu_field")
@Accessors(chain = true)
@DynamicUpdate
public class MenuField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String icon;

    @Column
    private String command;

    @Column
    private Long shortOrder;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Menu.class)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "menu_field_id")
    private List<MenuField> menuFieldList;

}
