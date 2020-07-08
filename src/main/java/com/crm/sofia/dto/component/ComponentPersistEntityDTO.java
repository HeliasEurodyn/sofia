package com.crm.sofia.dto.component;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.model.persistEntity.PersistEntity;
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
public class ComponentPersistEntityDTO extends BaseDTO {

    private PersistEntityDTO persistEntity;

    private String code;

    private String selector;

    private List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList;

}
