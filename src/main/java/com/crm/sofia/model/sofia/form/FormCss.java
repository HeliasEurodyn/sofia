package com.crm.sofia.model.sofia.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "FormCss")
@Table(name = "form_css")
public class FormCss extends BaseEntity {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String script;

    @Column(name = "short_order")
    private Long shortOrder;
}
