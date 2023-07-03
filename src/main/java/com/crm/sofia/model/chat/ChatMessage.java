package com.crm.sofia.model.chat;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import com.crm.sofia.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "chat_message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessage extends BaseEntity {
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;




}
