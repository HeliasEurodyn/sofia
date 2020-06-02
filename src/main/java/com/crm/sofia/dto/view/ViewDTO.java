package com.crm.sofia.dto.view;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.model.table.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ViewDTO extends BaseDTO {

    private String name;
    private String description;
    private String query;
    private List<ViewFieldDTO> viewFieldList;

}
