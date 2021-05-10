package com.crm.sofia.dto.chart;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ChartDTO extends BaseDTO {

    private String title;

    private String secondTitle;

    private String chartJson;

    private String optionsJson;

    private String query;

    private String horizontalAxe;

    private List<ChartFieldDTO> chartFieldList;

    private Boolean executePeriodically;

    private Integer executionInterval;

}
