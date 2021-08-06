package com.crm.sofia.dto.sofia.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.form.designer.FormActionButtonDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class FormUiDTO extends BaseDTO {

    private String name;

    private String title;

    private String description;

    private String icon;

    private List<FormUiTabDTO> formTabs;

    private List<FormPopupDto> formPopups;

    private List<FormScriptDTO> formScripts;

    private String jsonUrl;

    private List<FormActionButtonUiDTO> formActionButtons;
}
