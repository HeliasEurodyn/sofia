package com.crm.sofia.repository.chat;

import com.crm.sofia.model.chat.ChatRoom;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends BaseRepository<ChatRoom> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
