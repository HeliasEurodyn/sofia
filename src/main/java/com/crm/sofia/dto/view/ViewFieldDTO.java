package com.crm.sofia.dto.view;

import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ViewFieldDTO extends PersistEntityFieldDTO {
}
