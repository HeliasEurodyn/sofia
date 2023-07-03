package com.crm.sofia.dto.chat;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class MessageDTO extends BaseDTO {

    private String chatId;
    private String content;
    private String status;
    private String senderId;
    private String senderName;
    private String recipientId;
    private String recipientName;

}
