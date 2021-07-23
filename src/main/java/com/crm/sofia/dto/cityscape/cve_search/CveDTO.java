package com.crm.sofia.dto.cityscape.cve_search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CveDTO {

    @JsonProperty("id")
    String id;

    @JsonProperty("refmap")
    String refmap;

    @JsonProperty("vulnerable_configuration")
    String vulnerableConfiguration;

    @JsonProperty("vulnerable_configuration_cpe_2_2")
    String vulnerableConfigurationCpe2_2;

    @JsonProperty("vulnerable_product")
    String vulnerableProduct;

    @JsonProperty("msbulletin")
    String msbulletin;

    @JsonProperty("Modified")
    String modified;

    @JsonProperty("Published")
    String published;

    @JsonProperty("access")
    String access;

    @JsonProperty("assigner")
    String assigner;

    @JsonProperty("cvss")
    String cvss;

    @JsonProperty("impactScore")
    String impactScore;

    @JsonProperty("exploitabilityScore")
    String exploitabilityScore;

    @JsonProperty("cvss-time")
    String cvssTime;

    @JsonProperty("cvss-vector")
    String cvssVector;

    @JsonProperty("cwe")
    String cwe;

    @JsonProperty("impact")
    String impact;

    @JsonProperty("impact3")
    String impact3;

    @JsonProperty("exploitability3")
    String exploitability3;

    @JsonProperty("cvss3")
    String cvss3;

    @JsonProperty("impactScore3")
    String impactScore3;

    @JsonProperty("exploitabilityScore3")
    String exploitabilityScore3;

    @JsonProperty("cvss3-vector")
    String cvss3Vector;

    @JsonProperty("last-modified")
    String lastModified;

    @JsonProperty("references")
    String references;

    @JsonProperty("summary")
    String summary;

}
