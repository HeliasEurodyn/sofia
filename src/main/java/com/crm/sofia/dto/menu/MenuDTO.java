package com.crm.sofia.dto.menu;

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
public class MenuDTO extends BaseDTO {

    private String name;

    private Boolean accessControlEnabled;

    private List<MenuFieldDTO> menuFieldList;

    private List<AccessControlDTO> accessControls;

    private List<MenuTranslationDTO> translations;
}
