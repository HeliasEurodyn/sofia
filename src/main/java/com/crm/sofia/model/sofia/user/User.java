package com.crm.sofia.model.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;

    @Column(name = "enabled", columnDefinition = "BIT", length = 1)
    private boolean enabled;

    @Column
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

    @Column
    private String provider;


    // bi-directional many-to-many association to Role
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private List<Role> roles;

    public Set<Role> getRolesSet(){
        return new HashSet<>(roles);
    }

    public void setRolesSet(Set<Role> rolesSet){
        this.roles = new ArrayList(rolesSet);
    }
}
