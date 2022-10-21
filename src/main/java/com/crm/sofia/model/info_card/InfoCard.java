package com.crm.sofia.model.info_card;

import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "InfoCard")
@Table(name = "info_card")
public class InfoCard extends MainEntity {

    @Column
    private String title;

    @Column
    private String icon;

    @Column
    private String description;

    @Column
    private String cardText;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private String command;

    @Column
    private String commandIcon;

    @Column
    private Boolean executePeriodically;

    @Column
    private Integer executionInterval;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "info_card_id")
    private List<InfoCartScript> scripts;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String script;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String scriptMin;

}
