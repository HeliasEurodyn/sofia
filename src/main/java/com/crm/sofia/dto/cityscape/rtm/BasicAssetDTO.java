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

    private Long id;

    private String name;

    private String description;

    private Long type_id;

    private String type;

    private String category;

    private String vendor;

    private String product;

    private String version;

    private String cpe;

    private List<ThreatDTO> threats;
}
