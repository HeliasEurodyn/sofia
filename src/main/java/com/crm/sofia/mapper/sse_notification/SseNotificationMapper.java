package com.crm.sofia.mapper.sse_notification;

import com.crm.sofia.dto.sofia.sse_notification.SseNotificationDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.sse_notification.SseNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SseNotificationMapper extends BaseMapper<SseNotificationDTO, SseNotification> {

    public List<SseNotificationDTO> mapEntitiesForList(List<SseNotification> entities) {
        return entities.stream().map(this::mapEntityIgnoringQuery).collect(Collectors.toList());
    }

    @Mapping(ignore = true, target = "query")
    public abstract SseNotificationDTO mapEntityIgnoringQuery(SseNotification entity);

}
