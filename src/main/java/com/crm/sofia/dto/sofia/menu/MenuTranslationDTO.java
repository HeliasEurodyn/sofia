package com.crm.sofia.dto.sofia.menu;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.language.LanguageDTO;
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
public class MenuTranslationDTO extends BaseDTO {

    private String name;

    private LanguageDTO language;
}
