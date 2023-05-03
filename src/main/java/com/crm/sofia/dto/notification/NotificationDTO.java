package com.crm.sofia.dto.notification;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class NotificationDTO extends BaseDTO {

    private String title;
    private String message;
    private String status;
    private String icon;
    private String senderId;
    private String receiverId;
    private String command;

    public NotificationDTO(String id, String title) {
        this.setId(id);
        this.title = title;
    }

}
