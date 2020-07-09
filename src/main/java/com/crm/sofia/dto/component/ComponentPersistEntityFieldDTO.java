package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ComponentPersistEntityFieldDTO extends BaseDTO {

    private String description;

    private String editor;

    private String defaultValue;

    private String saveStatement;

    private PersistEntityFieldDTO persistEntityField;


}