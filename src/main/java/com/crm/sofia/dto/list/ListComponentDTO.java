package com.crm.sofia.dto.list;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentDTO;
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
public class ListComponentDTO extends BaseDTO {
    private String code;
    private String selector;
    private ComponentDTO component;
    private List<ListComponentFieldDTO> listComponentColumnFieldList;
    private List<ListComponentFieldDTO> listComponentFilterFieldList;
    private List<ListComponentFieldDTO> listComponentLeftGroupFieldList;
    private List<ListComponentFieldDTO> listComponentTopGroupFieldList;
    private List<ListComponentFieldDTO> listComponentOrderByFieldList;
    private List<ListComponentFieldDTO> listComponentActionFieldList;
    private String filterFieldStructure;
    private Boolean customFilterFieldStructure;

}
