package com.crm.sofia.model.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.menu.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "User")
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(updatable = false, nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AppConstants.Types.UserStatus status;

    @Column(name = "dateformat")
    private String dateFormat;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Menu.class)
    @JoinColumn(name = "sidebar_menu_id", referencedColumnName = "id")
    private Menu sidebarMenu;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Menu.class)
    @JoinColumn(name = "header_menu_id", referencedColumnName = "id")
    private Menu headerMenu;

    @Column
    private String loginNavCommand;

    @Column
    private String searchNavCommand;

}
