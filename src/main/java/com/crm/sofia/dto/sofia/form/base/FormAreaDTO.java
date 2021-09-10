package com.crm.sofia.dto.sofia.form.base;

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
public class FormAreaDTO extends BaseDTO {

    public String code;

    public String title;

    private String description;

    private String icon;

    private String cssclass;

    private List<FormControlDTO> formControls;

}
