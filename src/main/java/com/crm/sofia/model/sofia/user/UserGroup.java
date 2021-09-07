package com.crm.sofia.model.sofia.user;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "UserGroup")
@Table(name = "user_group")
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "user_to_user_group",
            joinColumns = @JoinColumn(name = "user_group_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }
    )
    private List<User> users;

}
