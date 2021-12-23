package com.crm.sofia.model.sofia.list;

import com.crm.sofia.dto.sofia.list.base.ListActionButtonDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.list.translation.ListActionButtonTranslation;
import com.crm.sofia.model.sofia.list.translation.ListTranslation;
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

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "parent_id")
    List<ListActionButton> listActionButtons;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "list_action_button_id")
    private List<ListActionButtonTranslation> translations;
}
