package com.crm.sofia.dto.menu;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class MenuFieldDTO extends BaseDTO {

    private String name;

    private String icon;

    private String command;

    private List<MenuFieldDTO> menuFieldList;

    private List<MenuTranslationDTO> translations;
}
