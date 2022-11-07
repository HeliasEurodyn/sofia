package com.crm.sofia.dto.custom_query;

import com.crm.sofia.dto.access_control.AccessControlDTO;
import com.crm.sofia.dto.common.BaseDTO;
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
public class CustomQueryDTO extends BaseDTO {

    private String code;

    private String name;

    private String query;

    private List<CustomQueryFieldDTO> filters;

    private List<CustomQueryFieldDTO> columns;

    private Boolean accessControlEnabled;

    private List<AccessControlDTO> accessControls;
}
