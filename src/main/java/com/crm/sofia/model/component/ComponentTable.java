package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "ComponentTable")
@Table(name = "component_table")
@Accessors(chain = true)
@DynamicUpdate
public class ComponentTable extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.table.Table.class)
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private com.crm.sofia.model.table.Table table;

    @Column
    private String code;

    @Column
    private String selector;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "component_table_id")
    private List<ComponentTableField> componentTableFieldList;

}
