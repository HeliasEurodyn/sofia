package com.crm.sofia.dto.list.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.list.user.translation.ListActionSubButtonTranslationDTO;
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
public class ListActionSubButtonUiDTO extends BaseDTO {
    private String code;
    private String icon;
    private String description;
    private String editor;
    private String cssClass;
    private Boolean visible;
    private List<ListActionSubButtonTranslationDTO> translations;
}
