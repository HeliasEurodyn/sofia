package com.crm.sofia.dto.cityscape.rtm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class BasicAssetDTO {

    private String basic_asset_name;

    private String basic_asset_type;

    private String basic_asset_type_id;

    private String basic_asset_part;

    private String basic_asset_vendor;

    private String basic_asset_product;

    private String basic_asset_version;

    private String basic_asset_update;

    private String basic_asset_edition;

    private String basic_asset_language;

    private String basic_asset_sw_edition;

    private String basic_asset_target_sw;

    private String basic_asset_target_hw;

    private String basic_asset_other;

    private ImpactDTO impact;

    private List<ThreatDTO> threats;
}
