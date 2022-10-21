package com.crm.sofia.dto.security;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.dto.user.UserGroupDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class SecurityDTO extends BaseDTO {
    @NotNull
    private String type;
    @NotNull
    private Long entityId;
    @NotNull
    private Boolean create;
    @NotNull
    private Boolean update;
    @NotNull
    private Boolean read;
    @NotNull
    private Boolean delete;
    @NotNull
    private UserDTO user;
    @NotNull
    private UserGroupDTO userGroup;
}
