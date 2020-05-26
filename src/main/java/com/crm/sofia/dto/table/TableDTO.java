package com.crm.sofia.dto.table;

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
public class TableDTO extends BaseDTO {

    private String name;
    private Integer creationVersion;
    private String indexes;
    private String description;
    private List<TableFieldDTO> tableFieldList;

}
