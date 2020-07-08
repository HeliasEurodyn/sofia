package com.crm.sofia.dto.persistEntity;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "entitytype") @JsonSubTypes({
        @JsonSubTypes.Type(value = TableDTO.class, name = "table"),
        @JsonSubTypes.Type(value = ViewDTO.class, name = "view"),
        @JsonSubTypes.Type(value = AppViewDTO.class, name = "appview")
})
public class PersistEntityDTO extends BaseDTO{

    private String name;

    private String description;
}


