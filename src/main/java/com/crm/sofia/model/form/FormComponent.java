package com.crm.sofia.model.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormComponent")
@Table(name = "form_component")
public class FormComponent extends BaseEntity {

    @Column
    private String type;

    @Column
    private String cssclass;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_component_id")
    private FormComponentField formComponentField;

}
