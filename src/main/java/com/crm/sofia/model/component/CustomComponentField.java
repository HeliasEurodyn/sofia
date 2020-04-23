package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;



@Data
@Getter
@Setter
@Entity(name = "CustomComponentField")
@Table(name = "custom_component_field")
@Accessors(chain = true)
@DynamicUpdate
public class CustomComponentField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

    @Column
    private String relatedComponentName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CustomComponent.class)
    @JoinColumn(name = "custom_component_id", referencedColumnName = "id")
    private CustomComponent customComponent;

}
