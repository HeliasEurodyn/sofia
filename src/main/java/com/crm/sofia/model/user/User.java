package com.crm.sofia.model.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.menu.Menu;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "User")
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

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
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

}
