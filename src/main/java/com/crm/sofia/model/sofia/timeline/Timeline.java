package com.crm.sofia.model.sofia.timeline;


import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity(name = "Timeline")
@Table(name = "timeline")
public class Timeline extends MainEntity {

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String query;

}
