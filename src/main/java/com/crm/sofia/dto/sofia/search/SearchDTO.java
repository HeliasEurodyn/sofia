package com.crm.sofia.dto.sofia.search;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.access_control.AccessControlDTO;
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
public class SearchDTO extends BaseDTO {

    private String name;

    private String query;

    private Boolean accessControlEnabled;

    private List<AccessControlDTO> accessControls;
}
