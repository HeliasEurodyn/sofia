package com.crm.sofia.dto.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.form.user.translation.FormAreaTranslationDTO;
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
public class FormUiAreaDTO extends BaseDTO {

    public String code;

    public String title;

    private String description;

    private String icon;

    private String cssclass;

    private List<FormUiControlDTO> formControls;

//    private List<FormAreaTranslationDTO> translations;
}
