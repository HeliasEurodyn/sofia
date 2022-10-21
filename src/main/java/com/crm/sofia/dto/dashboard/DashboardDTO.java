package com.crm.sofia.dto.dashboard;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.access_control.AccessControlDTO;
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
public class DashboardDTO extends BaseDTO {

    private String description;

    private List<DashboardAreaDTO> dashboardAreaList;

    private Boolean accessControlEnabled;

    private List<AccessControlDTO> accessControls;
}
