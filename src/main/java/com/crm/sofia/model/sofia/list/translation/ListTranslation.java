package com.crm.sofia.model.sofia.list.translation;

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
@Entity(name = "ListTranslation")
@Table(name = "list_translation")
public class ListTranslation extends MainEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @Column
    private String headerTitle;

    @Column(length=1024)
    private String HeaderDescription;

    @Column
    private String title;

    @Column(length=1024)
    private String description;

    @Column
    private String groupingTitle;

    @Column(length=1024)
    private String groupingDescription;

}
