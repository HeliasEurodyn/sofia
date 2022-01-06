package com.crm.sofia.dto.sofia.list.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.list.base.translation.ListActionButtonTranslationDTO;
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
public class ListActionButtonUiDTO extends BaseDTO {
    private String code;
    private String icon;
    private String description;
    private String editor;
    private String cssClass;
    private Boolean visible;
    List<ListActionSubButtonUiDTO> listActionButtons;
    private List<ListActionButtonTranslationDTO> translations;
}
