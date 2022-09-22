package com.crm.sofia.dto.sofia.timeline;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private boolean hasPagination;

    private Integer pageSize;

    private Boolean isTheLastPage;

    private List<ListComponentFieldDTO> filterList;

}
