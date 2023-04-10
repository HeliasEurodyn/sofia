package com.crm.sofia.dto.settings;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class SettingsDto extends BaseDTO {

    String name;

    String sidebarImage;

    String loginImage;

    String icon;

    String oauth_prototype_user_id;


    String mailSenderHost;


    String mailSenderPort;


    String mailSenderUsername;


    String mailSenderPassword;

    public SettingsDto(String mailSenderHost, String mailSenderPort, String mailSenderUsername, String mailSenderPassword) {

        this.mailSenderHost = mailSenderHost;
        this.mailSenderPort = mailSenderPort;
        this.mailSenderUsername = mailSenderUsername;
        this.mailSenderPassword = mailSenderPassword;
    }
}
