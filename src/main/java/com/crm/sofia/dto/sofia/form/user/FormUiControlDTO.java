package com.crm.sofia.dto.sofia.form.user;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"createdOn","createdBy","shortOrder","version"})
@Accessors(chain = true)
public class FormUiControlDTO extends BaseDTO {

    private String type;

    private String cssclass;
    
    private FormControlFieldDTO formControlField;

    public FormControlTableDTO formControlTable;

    public FormControlButtonDTO formControlButton;

}
