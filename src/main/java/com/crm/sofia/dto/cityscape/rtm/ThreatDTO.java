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
public class ThreatDTO {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Double probability_of_occurrence;

    private ThreatImpactSelectionDTO impact;

    private List<CountermeasureDTO> countermeasures;

    private List<RiskDTO> risk;

}
