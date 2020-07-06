package com.crm.sofia.model.component;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.table.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@Getter
@Setter
@Entity(name = "ComponentField")

@javax.persistence.Table(name = "component_field")
@Accessors(chain = true)
@DynamicUpdate
public class ComponentTableField extends BaseEntity {

    @Column
    private String description;

    @Column
    private String editor;

    @Column
    private String defaultValue;

    @Column
    private String saveStatement;


//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Component.class)
//    @JoinColumn(name = "component_id", referencedColumnName = "id")
//    private Component component;

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Table.class)
//    @JoinColumn(name = "table_id", referencedColumnName = "id")
//    private Table table;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TableField.class)
    @JoinColumn(name = "table_field_id", referencedColumnName = "id")
    private TableField tableField;

//    @OneToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.ALL})
//    @JoinColumn(name = "component_field_id")
//    private List<ComponentTableField> componentTableFieldList;


//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.table.Table.class)
//    @JoinColumn(name = "table_id", referencedColumnName = "id")
//    private com.crm.sofia.model.table.Table table;

}
