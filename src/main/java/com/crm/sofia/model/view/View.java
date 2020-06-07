package com.crm.sofia.model.view;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.table.TableField;
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
@Entity(name = "View")
@javax.persistence.Table(name = "custom_view")
@Accessors(chain = true)
@DynamicUpdate
public class View extends BaseEntity {

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
    private List<ViewField> viewFieldList;


}