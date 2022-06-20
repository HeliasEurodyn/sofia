package com.crm.sofia.model.cityscape.countermeasure;

import com.crm.sofia.model.cityscape.threat.Threat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Table(name = "countermeasure")
@Entity(name = "Countermeasure")
public class Countermeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_by", length = 36)
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "modified_by", length = 36)
    private String modifiedBy;

    @Column(name = "modified_on")
    private Instant modifiedOn;
//
//    @Column(name = "short_order")
//    private Long shortOrder;
//
//    @Column(name = "version")
//    private Long version;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 500)
    private String description;

//    @Column(name = "type", length = 50)
//    private String type;

    @Column(name = "cis_control_id")
    private Long cisControlId;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "threat_to_countermeasure",
            joinColumns = @JoinColumn(name = "countermeasure_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "threat_id", referencedColumnName = "id")
            }
    )
    private List<Threat> threats;
}
