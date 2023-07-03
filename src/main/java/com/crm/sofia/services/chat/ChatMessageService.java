package com.crm.sofia.services.chat;

import com.crm.sofia.dto.chat.MessageDTO;
import com.crm.sofia.exception.ResourceNotFoundException;
import com.crm.sofia.mapper.chat.ChatMapper;
import com.crm.sofia.model.chat.ChatMessage;
import com.crm.sofia.model.chat.MessageStatus;
import com.crm.sofia.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    final private ChatMessageRepository chatMessageRepository;
    final private ChatRoomService chatRoomService;

    final private ChatMapper chatMapper;


    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<MessageDTO> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        List<ChatMessage> messages = chatId.map(cId -> chatMessageRepository.findByChatIdOrderByTimestamp(cId)).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        List<MessageDTO> messagesDTO =chatMapper.map(messages);

        return messagesDTO;
    }

    public ChatMessage findById(String id) {
        return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("message","id",id));
    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        List<ChatMessage> messages = chatMessageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        for (ChatMessage message : messages) {
            message.setStatus(status);
        }
        chatMessageRepository.saveAll(messages);
    }

}
