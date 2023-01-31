package com.crm.sofia.dto.list.translation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.language.LanguageDTO;
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
public class ListTranslationDTO extends BaseDTO {

    private LanguageDTO language;

    private String headerTitle;
    private String HeaderDescription;

    private String title;
    private String description;

    private String groupingTitle;
    private String groupingDescription;

}
