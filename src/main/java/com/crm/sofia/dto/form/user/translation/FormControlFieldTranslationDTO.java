package com.crm.sofia.dto.form.user.translation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.language.LanguageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class FormControlFieldTranslationDTO extends BaseDTO {
    private LanguageDTO language;
    private String description;
    private String message;
    private String placeholder;
}
