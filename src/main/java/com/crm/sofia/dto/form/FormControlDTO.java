package com.crm.sofia.dto.form;

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
public class FormControlDTO extends BaseDTO {

    private String type;

    private String cssclass;
    
    private FormControlFieldDTO formControlField;

    public FormControlTableDTO formControlTable;


}
