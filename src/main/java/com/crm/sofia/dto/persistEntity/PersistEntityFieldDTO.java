package com.crm.sofia.dto.persistEntity;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.appview.AppViewFieldDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.view.ViewFieldDTO;
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
        @JsonSubTypes.Type(value = TableFieldDTO.class, name = "tablefield"),
        @JsonSubTypes.Type(value = ViewFieldDTO.class, name = "viewfield"),
        @JsonSubTypes.Type(value = AppViewFieldDTO.class, name = "appviewfield")
})
public class PersistEntityFieldDTO extends BaseDTO {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private Integer size;

}
