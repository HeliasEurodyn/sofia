package com.crm.sofia.dto.list;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ListComponentFieldDTO extends BaseDTO {
    private String editor;
    private String description;
    private String type;
    private ComponentPersistEntityDTO componentTable;
    private ComponentPersistEntityFieldDTO componentTableField;
    private Boolean visible;
    private Boolean editable;
}
