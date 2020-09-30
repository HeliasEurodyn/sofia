package com.crm.sofia.model.appview;

import com.crm.sofia.model.persistEntity.PersistEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "AppView")
@DiscriminatorValue("AppView")
public class AppView extends PersistEntity {


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "persist_entity_id")
    private List<AppViewField> appViewFieldList;


}
