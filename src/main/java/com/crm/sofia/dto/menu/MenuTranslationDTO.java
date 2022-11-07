package com.crm.sofia.dto.menu;

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
public class MenuTranslationDTO extends BaseDTO {

    private String name;

    private LanguageDTO language;
}
