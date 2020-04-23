package com.crm.sofia.dto.component;

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
public class CustomComponentDTO extends BaseDTO {

    private String name;
    private Integer creationVersion;
    private String indexes;
    private String description;
    private List<CustomComponentFieldDTO> customComponentFieldList;

}
