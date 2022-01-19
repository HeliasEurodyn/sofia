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

    private Long id;

    private String name;

    private CompositeAssetEconomicValuesDTO economic;

    private List<CompositeAssetCommunicationLinkDTO> communication_links;

    private List<BasicAssetDTO> basic_assets;

}
