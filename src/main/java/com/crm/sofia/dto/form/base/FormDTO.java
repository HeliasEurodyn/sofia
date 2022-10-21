package com.crm.sofia.dto.form.base;

import com.crm.sofia.dto.access_control.AccessControlDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentDTO;
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
public class FormDTO extends BaseDTO {

    private String name;

    private String title;

    private String icon;

    private String description;

    private Boolean accessControlEnabled;

    private String businessUnit;

    private ComponentDTO component;

    private List<FormTabDTO> formTabs;

    private List<FormPopupDto> formPopups;

    private List<FormScriptDTO> formScripts;

    private List<FormCssDTO> formCssList;

    private String jsonUrl;

    private List<FormActionButtonDTO> formActionButtons;

    private Long instanceVersion;

    private List<AccessControlDTO> accessControls;
}
