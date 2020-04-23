package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
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
public class CustomComponentFieldDTO extends BaseDTO {

    private String name;
    private String description;
    private String type;
    private Integer size;
    private String relatedComponentName;
   // private CustomComponentDTO customComponent;

}
