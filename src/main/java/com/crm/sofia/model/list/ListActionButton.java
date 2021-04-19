package com.crm.sofia.model.list;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListActionButton")
@Table(name = "list_action_button")
public class ListActionButton extends BaseEntity {

    @Column
    private String code;

    @Column
    private String icon;

    @Column
    private String description;

    @Column
    private String editor;

    @Column
    private String cssClass;

    @Column
    private Boolean visible;
}
