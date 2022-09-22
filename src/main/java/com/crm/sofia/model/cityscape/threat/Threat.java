package com.crm.sofia.model.cityscape.threat;

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
@Table(name = "threat")
@Entity(name = "Threat")
public class Threat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "capec", length = 20)
    private String capec;

    @Column(name = "cwe", length = 20)
    private String cwe;

    @Column(name = "confidentiality")
    private Integer confidentiality;

    @Column(name = "integrity")
    private Integer integrity;

    @Column(name = "accountability")
    private Integer accountability;

    @Column(name = "system_threat")
    private Integer systemThreat;

    @Column(name = "created_by", length = 36)
    private String createdBy;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "modified_by", length = 36)
    private String modifiedBy;

    @Column(name = "modified_on", nullable = false)
    private Instant modifiedOn;

    @Column(name = "short_order")
    private Long shortOrder;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "threat_to_asset_type_group",
            joinColumns = @JoinColumn(name = "threat_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "asset_type_group_id", referencedColumnName = "id")
            }
    )
    private List<AssetTypeGroup> assetTypeGroups;
}
