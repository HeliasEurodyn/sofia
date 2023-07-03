package com.crm.sofia.repository.chat;

import com.crm.sofia.model.chat.ChatMessage;
import com.crm.sofia.model.chat.ChatRoom;
import com.crm.sofia.model.chat.MessageStatus;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends BaseRepository<ChatMessage> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatIdOrderByTimestamp(String chatId);

    List<ChatMessage> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
