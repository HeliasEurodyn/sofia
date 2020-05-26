package com.crm.sofia.model.table;

import com.crm.sofia.model.common.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;



@Data
@Getter
@Setter
@Entity(name = "TableField")
@javax.persistence.Table(name = "custom_table_field")
@Accessors(chain = true)
@DynamicUpdate
public class TableField extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Table.class)
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private Table table;

    @Column
    private Long shortOrder;

    @Column
    private Boolean autoIncrement;

    @Column
    private Boolean primaryKey;

}
