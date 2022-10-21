package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
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
@Entity(name = "FormPopup")
@Table(name = "Form_popup")
public class FormPopup extends BaseEntity {

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private String icon;

    @Column
    private Boolean editable;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_popup_id")
    private List<FormArea> formAreas;

    @Column(name = "short_order")
    private Long shortOrder;
}
