package com.crm.sofia.dto.cityscape.rtm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ServiceCommunicationLinkDTO {

    private Long id;

    private Long service_id_source;

    private Long composite_id_source;

    private Long basic_asset_id_source;

    private Long service_id_destination;

    private Long composite_id_destination;

    private Long basic_asset_id_destination;

}
