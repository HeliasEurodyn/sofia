package com.crm.sofia.dto.sofia.form.base;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class FormControlTableControlDTO extends BaseDTO {

    private String type;

    private String cssclass;

    private FormControlFieldDTO formControlField;

    private FormControlButtonDTO formControlButton;
}
