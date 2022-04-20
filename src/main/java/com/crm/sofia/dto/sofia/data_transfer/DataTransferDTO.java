package com.crm.sofia.dto.sofia.data_transfer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class DataTransferDTO extends BaseDTO {

    private String name;

    private String description;

    private Integer currentVersion;

    List<String> formIds;

    List<FormDTO> forms;

    List<String> listIds;

    List<ListDTO> lists;

}
