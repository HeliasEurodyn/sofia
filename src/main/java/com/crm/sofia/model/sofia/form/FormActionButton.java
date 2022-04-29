package com.crm.sofia.model.sofia.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "FormActionButton")
@Table(name = "form_action_button")
public class FormActionButton extends BaseEntity {

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

    @Column
    private Boolean editable;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "parent_id")
    private List<FormActionButton> formActionButtons;

    @Column(name = "short_order")
    private Long shortOrder;
}
