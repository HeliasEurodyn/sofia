package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormControlTableControl")
@Table(name = "form_control_table_control")
public class FormControlTableControl extends BaseEntity {

    @Column
    private String type;

    @Column
    private String cssclass;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_control_field_id")
    private FormControlField formControlField;

    @Column(name = "short_order")
    private Long shortOrder;
}
