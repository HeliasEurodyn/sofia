package com.crm.sofia.dto.rest_documentation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.model.list.ListComponentField;
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
public class RestDocumentationListDTO extends BaseDTO {

    private String jsonUrl;

    private List<ListComponentFieldDTO> listComponentFilterFieldList;

}
