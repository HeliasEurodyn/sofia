package com.crm.sofia.dto.sofia.chart;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ChartFieldDTO extends BaseDTO {

    private String name;

    private String type;

    private Integer size;

    private String chartJson;

    private String description;

    private List<Object> dataset;

}
