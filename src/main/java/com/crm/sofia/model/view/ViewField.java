package com.crm.sofia.model.view;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.table.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity(name = "ViewField")
@javax.persistence.Table(name = "custom_view_field")
@Accessors(chain = true)
@DynamicUpdate
public class ViewField extends BaseEntity {


    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = View.class)
//    @JoinColumn(name = "view_id", referencedColumnName = "id")
//    private View view;

}
