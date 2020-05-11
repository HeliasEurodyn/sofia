package com.crm.sofia.dto.menu;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.menu.MenuItemComponent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class MenuComponentDTO extends BaseDTO {

    private String name;

    private Integer linecounter;

    private List<MenuItemComponentDTO> menuFieldList;
}
