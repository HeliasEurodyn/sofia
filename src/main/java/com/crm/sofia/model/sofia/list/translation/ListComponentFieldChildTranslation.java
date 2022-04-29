package com.crm.sofia.model.sofia.list.translation;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.language.Language;
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
@Entity(name = "ListComponentFieldChildTranslation")
@Table(name = "list_component_field_child_translation")
public class ListComponentFieldChildTranslation extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;


    @Column(length=1024)
    private String description;

}
