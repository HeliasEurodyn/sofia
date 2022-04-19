package com.crm.sofia.model.sofia.menu;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.access_control.AccessControl;
import com.crm.sofia.model.sofia.language.Language;
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
@Entity(name = "MenuTranslation")
@Table(name = "menu_translation")
public class MenuTranslation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @Column
    private String name;
}
