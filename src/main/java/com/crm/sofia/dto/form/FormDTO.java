package com.crm.sofia.dto.form;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentDTO;
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

    private ComponentDTO component;

    private List<FormTabDTO> formTabs;

    private List<FormScriptDTO> formScripts;

}
