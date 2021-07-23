package com.crm.sofia.dto.sofia.form.designer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
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
public class FormControlTableDTO extends BaseDTO {

    private String description;

    private Boolean visible;

    private Boolean editable;

    private Boolean required;

    private String css;

    private ComponentPersistEntityDTO componentPersistEntity;

    private List<FormControlTableControlDTO> formControls;

    private List<FormControlTableControlDTO> formControlButtons;
}
