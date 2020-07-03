package com.crm.sofia.model.appview;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Getter
@Setter
@Entity(name = "AppViewField")
@javax.persistence.Table(name = "app_view_field")
@Accessors(chain = true)
@DynamicUpdate
public class AppViewField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

}
