package com.crm.sofia.model.list;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentTableDTO;
import com.crm.sofia.dto.component.ComponentTableFieldDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.ComponentTable;
import com.crm.sofia.model.component.ComponentTableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

//@Data
//@Getter
//@Setter
//@Entity(name = "ListComponentFilterField")
//@Table(name = "list_component_filter_field")
//@Accessors(chain = true)
//@DynamicUpdate
public class ListComponentFilterField extends BaseEntity {
    @Column
    private String editor;

    @Column
    private String description;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.component.ComponentTable.class)
    @JoinColumn(name = "component_table_id", referencedColumnName = "id")
    private ComponentTable componentTable;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.component.ComponentTableField.class)
    @JoinColumn(name = "component_table_field_id", referencedColumnName = "id")
    private ComponentTableField componentTableField;

    @Column
    private Boolean  visible;

    @Column
    private Boolean editable;
}
