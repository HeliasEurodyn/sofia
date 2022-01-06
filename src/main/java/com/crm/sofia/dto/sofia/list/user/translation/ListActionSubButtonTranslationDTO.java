package com.crm.sofia.dto.sofia.list.user.translation;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.language.LanguageIdDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder", "version" })
public class ListActionSubButtonTranslationDTO extends BaseDTO implements Serializable {

    private LanguageIdDTO language;

    private String description;
}
