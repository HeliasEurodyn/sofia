package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentTableFieldDTO extends BaseDTO {

    private String description;

    private String editor;

//    private ComponentDTO component;

    private TableFieldDTO tableField;

//    private List<ComponentTableFieldDTO> componentFieldList;

//    private Long shortOrder;

//    private TableDTO table;

    private String defaultValue;

    @Column
    private String saveStatement;
}
