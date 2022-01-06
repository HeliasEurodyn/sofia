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
public class ListTranslationDTO extends BaseDTO implements Serializable {

    private LanguageIdDTO language;

    private String headerTitle;

    private String HeaderDescription;

    private String title;

    private String description;

    private String groupingTitle;

    private String groupingDescription;
}
