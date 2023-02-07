package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.form.translation.FormControlButtonTranslation;
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
@Entity(name = "FormControlButton")
@Table(name = "form_control_button")
public class FormControlButton extends BaseEntity {

    @Column
    private String code;

    @Column
    private String icon;

    @Column
    private String description;

    @Column
    private String editor;

    @Column
    private  Boolean visible;

    @Column
    private String cssClass;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_control_button_id")
    private List<FormControlButtonTranslation> translations;
}
