package com.crm.sofia.dto.sofia.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.component.user.ComponentUiDTO;
import com.crm.sofia.model.sofia.component.Component;
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
public class FormUiDTO extends BaseDTO {

    private String name;

    private String title;

    private String description;

    private String icon;

    private List<FormUiTabDTO> formTabs;

    private List<FormPopupDto> formPopups;

    private List<FormActionButtonUiDTO> formActionButtons;

    private Long instanceVersion;

}
