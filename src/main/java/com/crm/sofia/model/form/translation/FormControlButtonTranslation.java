package com.crm.sofia.model.form.translation;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.language.Language;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormControlButtonTranslation")
@Table(name = "form_control_button_translation")
public class FormControlButtonTranslation extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @Column(length=1024)
    private String description;
}
