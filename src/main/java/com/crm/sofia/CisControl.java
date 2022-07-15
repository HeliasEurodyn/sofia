package com.crm.sofia;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "cis_control")
@Entity
public class CisControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "security_function", length = 20)
    private String securityFunction;

    @Column(name = "cis_control_category_id")
    private Long cisControlCategoryId;

    @Column(name = "asset_type_group_id")
    private Long assetTypeGroupId;

    @Column(name = "cis_asset_type", length = 30)
    private String cisAssetType;

}
