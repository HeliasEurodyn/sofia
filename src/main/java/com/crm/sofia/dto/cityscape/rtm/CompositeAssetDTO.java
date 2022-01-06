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
public class CompositeAssetDTO {

    private String composite_id;

    private String composite_name;

    private String composite_asset_part;

    private String composite_asset_vendor;

    private String composite_asset_product;

    private String composite_asset_version;

    private String composite_asset_update;

    private String composite_asset_edition;

    private String composite_asset_language;

    private String composite_asset_sw_edition;

    private String composite_asset_target_sw;

    private String composite_asset_target_hw;

    private String composite_asset_other;

    private ImpactDTO impact;

    private List<CommunicationLinkDTO> communication_links;

    private List<BasicAssetDTO> basic_asset;

}
