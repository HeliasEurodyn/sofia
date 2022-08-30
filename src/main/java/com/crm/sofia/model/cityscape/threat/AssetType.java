package com.crm.sofia.model.cityscape.threat;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;


@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Table(name = "asset_type")
@Entity(name = "AssetType")
public class AssetType {
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

    @Column(name = "short_order")
    private Long shortOrder;

    @Column(name = "version")
    private Long version;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "asset_type_group_id")
    private Long assetTypeGroupId;

}
