package com.crm.sofia.model.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.crm.sofia.model.component.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormEntity")
@Table(name = "form")
public class FormEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = com.crm.sofia.model.component.Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "form_id")
    private List<FormTab> formTabs;


}
