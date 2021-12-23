package com.crm.sofia.dto.sofia.list.base.translation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.language.LanguageDTO;
import com.crm.sofia.model.sofia.language.Language;
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
public class ListActionButtonTranslationDTO extends BaseDTO {

    private LanguageDTO language;

    private String description;
}
