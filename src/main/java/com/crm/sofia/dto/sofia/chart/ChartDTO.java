package com.crm.sofia.dto.sofia.chart;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
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
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ChartDTO extends BaseDTO {

    private String title;

    private String icon;

    private String secondTitle;

    private String chartJson;

    private String optionsJson;

    private String query;

    private String horizontalAxe;

    private List<ChartFieldDTO> chartFieldList;

    private Boolean executePeriodically;

    private Boolean refreshButton;

    private Integer executionInterval;

    private List<ListComponentFieldDTO> filterList;
}
