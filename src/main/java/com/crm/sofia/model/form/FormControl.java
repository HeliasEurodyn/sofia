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
@Entity(name = "FormControl")
@Table(name = "form_control")
public class FormControl extends BaseEntity {

    @Column
    private String type;

    @Column
    private String cssclass;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_component_field_id")
    private FormControlField formControlField;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_component_table_id")
    private FormControlTable formControlTable;
}
