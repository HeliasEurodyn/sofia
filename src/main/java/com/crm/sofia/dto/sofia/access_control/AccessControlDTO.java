package com.crm.sofia.dto.sofia.access_control;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.user.RoleDTO;
import com.crm.sofia.model.sofia.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class AccessControlDTO extends BaseDTO {

    private String type;

    private Long entityId;

    private Boolean createEntity;

    private Boolean updateEntity;

    private Boolean readEntity;

    private Boolean deleteEntity;

    private RoleDTO role;
}
