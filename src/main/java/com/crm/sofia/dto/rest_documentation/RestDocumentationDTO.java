package com.crm.sofia.dto.rest_documentation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.rest_documentation.rest_documentation_endpoint.RestDocumentationEndpointDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class RestDocumentationDTO extends BaseDTO {

    @NotNull(message = "Title Cannot Be Null")
    @NotBlank(message = "Title Cannot Be Blank")
    private String title;

    private String description;

    private Boolean active;

    private List<RestDocumentationEndpointDTO> restDocumentationEndpoints;
}
