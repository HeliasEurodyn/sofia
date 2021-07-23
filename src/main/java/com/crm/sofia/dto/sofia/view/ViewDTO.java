package com.crm.sofia.dto.sofia.view;

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
public class ViewDTO extends BaseDTO {

    private String name;

    private String description;

    private String query;

    private Integer creationVersion;

    private String indexes;

    private List<ViewFieldDTO> viewFieldList;

    private String entitytype;
}
