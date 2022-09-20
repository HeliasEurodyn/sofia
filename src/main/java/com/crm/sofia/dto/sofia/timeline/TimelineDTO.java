package com.crm.sofia.dto.sofia.timeline;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class TimelineDTO extends BaseDTO {

    @NotNull
    @NotBlank
    private String title;

    private String description;

    private String icon;

    @NotNull
    private String query;

    private Boolean hasPagination;

    private Integer pageSize;

}
