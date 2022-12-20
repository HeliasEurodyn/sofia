package com.crm.sofia.model.settings;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity(name = "Settings")
@Table(name = "settings")
public class Settings extends BaseEntity {

    @Column
    String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    String sidebarImage;

    @Column(columnDefinition = "MEDIUMTEXT")
    String loginImage;

    @Column(columnDefinition = "MEDIUMTEXT")
    String icon;

    @Column(columnDefinition = "VARCHAR(36)")
    String oauth_prototype_user_id;

}
