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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "entitytype") @JsonSubTypes({
        @JsonSubTypes.Type(value = TableFieldDTO.class, name = "TableField"),
        @JsonSubTypes.Type(value = ViewFieldDTO.class, name = "ViewField"),
        @JsonSubTypes.Type(value = AppViewFieldDTO.class, name = "AppViewField")
})
public class PersistEntityFieldDTO extends BaseDTO {

    private String name;

    private String description;

    private String type;

    private Integer size;

    private String entitytype;

    private Boolean autoIncrement;
}
