package com.crm.sofia.model.appview;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.view.ViewField;
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
@Entity(name = "AppView")
@javax.persistence.Table(name = "app_view")
@Accessors(chain = true)
@DynamicUpdate
public class AppView extends BaseEntity {


    @Column
    private String name;

    @Column
    private String description;

    @Column(columnDefinition = "TEXT")
    private String query;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "view_id")
    private List<AppViewField> appViewFieldList;


}
