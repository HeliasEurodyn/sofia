package com.crm.sofia.dto.sofia.form.user;

import com.crm.sofia.dto.common.BaseDTO;
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
public class FormUiTabDTO extends BaseDTO {

    private String code;

    private String description;

    private String icon;

    private Boolean editable;

    private List<FormUiAreaDTO> formAreas;

}
