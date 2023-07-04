package com.crm.sofia.dto.rest_documentation.rest_documentation_endpoint;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.list.ListDTO;
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
public class RestDocumentationEndpointDTO extends BaseDTO {

    private ListDTO list;

    private FormDTO form;

    private String title;

    private String description;

    private String type;

    private String method;
}
