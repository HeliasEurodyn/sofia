package com.crm.sofia.mapper.chat;

import com.crm.sofia.dto.chat.MessageDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.chat.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ChatMapper extends BaseMapper<MessageDTO, ChatMessage> {
}
