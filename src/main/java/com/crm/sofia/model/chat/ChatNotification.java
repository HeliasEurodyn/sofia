package com.crm.sofia.model.chat;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@Table(name = "chat_notification")
public class ChatNotification extends BaseEntity {
    private String senderId;
    private String senderName;


    public ChatNotification(String id, String senderId, String senderName) {
        this.setId(id);
        this.senderId =senderId;
        this.senderName =senderName;
    }
}
