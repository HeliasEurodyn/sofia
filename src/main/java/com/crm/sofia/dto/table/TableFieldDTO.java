package com.crm.sofia.dto.table;

import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class TableFieldDTO extends PersistEntityFieldDTO {

    private Integer shortOrder;

    private Boolean autoIncrement;

    private Boolean primaryKey;

    private Boolean hasDefault;

    private String defaultValue;

    private Boolean isUnsigned;

    private Boolean hasNotNull;

}
