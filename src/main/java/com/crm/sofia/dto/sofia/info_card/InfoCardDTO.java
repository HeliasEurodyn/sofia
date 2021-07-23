package com.crm.sofia.dto.sofia.info_card;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class InfoCardDTO extends BaseDTO {

    private String title;

    private String icon;

    private String description;

    private String cardText;

    private String query;

    private String command;

    private String commandIcon;

    private Boolean executePeriodically;

    private Integer executionInterval;

}
