package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "CustomComponent")
@Table(name = "custom_component")
@Accessors(chain = true)
@DynamicUpdate
public class CustomComponent extends BaseEntity {

    @Column
    private String name;

    @Column
    private Integer creationVersion;

    @Column
    private String indexes;

    @Column
    private String description;

    @OneToMany(
            mappedBy = "customComponent",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE,CascadeType.REMOVE }
           // orphanRemoval = true
    )
    private List<CustomComponentField> customComponentFieldList;

}
