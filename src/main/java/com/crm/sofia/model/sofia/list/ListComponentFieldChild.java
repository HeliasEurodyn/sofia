package com.crm.sofia.model.sofia.list;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListComponentFieldChild")
@Table(name = "list_component_field_child")
public class ListComponentFieldChild extends BaseEntity implements Serializable {

    @Column
    private String code;

    @Column(columnDefinition = "TEXT")
    private String editor;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Boolean visible;

    @Column
    private Boolean editable;

    @Column
    private Boolean required;

    @Column
    private String defaultValue;

    @Column
    private Integer decimals;

    @Column
    private String fieldtype;

    @Column
    private String shortLocation;

    @Column
    private String bclass;

    @Column(columnDefinition = "TEXT")
    private String css;

    @Column
    private String formulaType;


}
