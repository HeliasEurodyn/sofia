package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentField;
import com.crm.sofia.model.table.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentFieldDTO extends BaseDTO {

    private String description;

    private String editor;

//    private ComponentDTO component;

    private TableFieldDTO tableField;

    private List<ComponentFieldDTO> componentFieldList;

    private Long shortOrder;

    private TableDTO table;

}
