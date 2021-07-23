package com.crm.sofia.dto.sofia.appview;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class AppViewFieldDTO extends BaseDTO {

    private String name;

    private String description;

    private String type;

    private Integer size;

    private Boolean autoIncrement;

    private Boolean primaryKey;

    private Boolean hasDefault;

    private String defaultValue;

    private Boolean isUnsigned;

    private Boolean hasNotNull;

    private String entitytype;
}
