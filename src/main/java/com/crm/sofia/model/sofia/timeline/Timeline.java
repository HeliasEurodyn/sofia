package com.crm.sofia.model.sofia.timeline;


import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.sofia.list.ListComponentField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity(name = "Timeline")
@Table(name = "timeline")
public class Timeline extends MainEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private Boolean hasPagination;

    @Column
    private Integer pageSize;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "timeline_id")
    private List<ListComponentField> filterList;

}
