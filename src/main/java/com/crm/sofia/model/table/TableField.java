package com.crm.sofia.model.table;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.BaseNoIdEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "TableField")
@javax.persistence.Table(name = "custom_table_field")
@DiscriminatorValue("TableField")
public class TableField extends PersistEntityField {

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Table.class)
//    @JoinColumn(name = "table_id", referencedColumnName = "id")
//    private Table table;

    @Column
    private Boolean autoIncrement;

    @Column
    private Boolean primaryKey;

    @Column
    private Boolean hasDefault;

    @Column
    private String defaultValue;

    @Column
    private Boolean isUnsigned;

    @Column
    private Boolean hasNotNull;

}
