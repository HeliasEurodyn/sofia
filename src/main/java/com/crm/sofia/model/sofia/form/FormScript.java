package com.crm.sofia.model.sofia.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormScript")
@Table(name = "form_script")
public class FormScript extends BaseEntity {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String script;

    @Column(name = "short_order")
    private Long shortOrder;
}
