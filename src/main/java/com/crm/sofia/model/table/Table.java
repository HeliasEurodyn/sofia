package com.crm.sofia.model.table;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.persistEntity.PersistEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name = "Table")
@javax.persistence.Table(name = "custom_table")
@Accessors(chain = true)
@DynamicUpdate
public class Table extends PersistEntity {

    @Column
    private Integer creationVersion;

    @Column
    private String indexes;

    @OneToMany(
            mappedBy = "table",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE,CascadeType.REMOVE }
           // orphanRemoval = true
    )
    private List<TableField> tableFieldList;

}
