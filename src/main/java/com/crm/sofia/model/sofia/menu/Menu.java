package com.crm.sofia.model.sofia.menu;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.access_control.AccessControl;
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
@Entity(name = "Menu")
@Table(name = "menu")
public class Menu extends MainEntity {

    @Column
    private Boolean accessControlEnabled;

    @Column
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "menu_id")
    private List<MenuField> menuFieldList;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "menu_id")
    private List<AccessControl> accessControls;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "menu_id")
    private List<MenuTranslation> translations;
}
