package com.crm.sofia.dto.appview;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class AppViewDTO extends BaseDTO {

    private String name;

    private String description;

    private String query;

    private Integer creationVersion;

    private String indexes;

    private List<AppViewFieldDTO> appViewFieldList;

    private String entitytype;

}
