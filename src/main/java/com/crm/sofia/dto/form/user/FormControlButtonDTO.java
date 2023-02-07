package com.crm.sofia.dto.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.form.user.translation.FormControlButtonTranslationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class FormControlButtonDTO extends BaseDTO {
    private String code;
    private String icon;
    private String description;
    private String editor;
    private  Boolean visible;
    private String cssClass;
//    private List<FormControlButtonTranslationDTO> translations;
}
