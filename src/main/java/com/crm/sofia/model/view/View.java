package com.crm.sofia.model.view;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.persistEntity.PersistEntity;
import com.crm.sofia.model.table.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "View")
//@javax.persistence.Table(name = "custom_view")
@DiscriminatorValue("View")
public class View extends PersistEntity {


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "persist_entity_id")
    private List<ViewField> viewFieldList;


}
