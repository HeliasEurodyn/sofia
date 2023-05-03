package com.crm.sofia.mapper.notification;

import com.crm.sofia.dto.notification.NotificationDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.notification.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class NotificationMapper extends BaseMapper<NotificationDTO, Notification> {
}
