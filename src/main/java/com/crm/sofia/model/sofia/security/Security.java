package com.crm.sofia.model.sofia.security;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.model.sofia.user.UserGroup;
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
public class Security extends MainEntity {

    @Column
    private String type;

    @Column
    private Long entityId;

    @Column(name= "createstatus")
    private Boolean create;

    @Column(name= "updatestatus")
    private Boolean update;

    @Column(name= "readstatus")
    private Boolean read;

    @Column(name= "deletestatus")
    private Boolean delete;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserGroup.class)
    @JoinColumn(name = "user_group_id", referencedColumnName = "id")
    private UserGroup userGroup;

}
