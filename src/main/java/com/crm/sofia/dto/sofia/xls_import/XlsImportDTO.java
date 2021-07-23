package com.crm.sofia.dto.sofia.xls_import;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class XlsImportDTO extends BaseDTO {

    private String code;

    private String name;

    private String description;

    private String icon;

    private Long firstLine;

    private String xlsIterationColumn;

    private List<XlsImportLineDTO> xlsImportLineList;

    private ComponentDTO component;
}
