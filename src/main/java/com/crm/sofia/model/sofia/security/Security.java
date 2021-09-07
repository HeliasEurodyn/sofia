package com.crm.sofia.model.sofia.security;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.menu.Menu;
import com.crm.sofia.model.sofia.user.User;
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
@Entity(name = "Security")
@Table(name = "security")
@EqualsAndHashCode(callSuper = true)
public class Security extends BaseEntity {

    @Column
    private String type;

    @Column
    private Long entityId;

    @Column
    private Boolean create;

    @Column
    private Boolean update;

    @Column
    private Boolean read;

    @Column
    private Boolean delete;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Menu.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Menu.class)
//    @JoinColumn(name = "user_group_id", referencedColumnName = "id")
//    UserGroup userGroup;

}
