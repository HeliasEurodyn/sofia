package com.crm.sofia.dto.sofia.menu;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.access_control.AccessControlDTO;
import com.crm.sofia.dto.sofia.list.base.translation.ListTranslationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
