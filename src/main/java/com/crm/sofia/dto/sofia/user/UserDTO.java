package com.crm.sofia.dto.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.menu.MenuDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class UserDTO extends BaseDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String repeatPassword;

    @NotNull
    private AppConstants.Types.UserStatus status;

    private String dateformat;

    private MenuDTO sidebarMenu;

    private MenuDTO headerMenu;

    private String loginNavCommand;

    private String searchNavCommand;

    private String provider;
}
