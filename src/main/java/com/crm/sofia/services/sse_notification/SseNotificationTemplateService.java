package com.crm.sofia.services.sse_notification;

import com.crm.sofia.dto.sse_notification.SseNotificationDTO;
import com.crm.sofia.mapper.sse_notification.SseNotificationMapper;
import com.crm.sofia.model.sse_notification.SseNotification;
import com.crm.sofia.repository.sse_notification.SseNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class SseNotificationTemplateService {


    @Autowired
    private SseNotificationMapper sseNotificationMapper;
    @Autowired
    private SseNotificationRepository sseNotificationRepository;

    public SseNotificationDTO getObject(String id) {
        Optional<SseNotification> optionalEntity = sseNotificationRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        SseNotification entity = optionalEntity.get();
        SseNotificationDTO dto = sseNotificationMapper.map(entity);

        String encodedQuery = Base64.getEncoder().encodeToString(dto.getQuery().getBytes(StandardCharsets.UTF_8));
        dto.setQuery(encodedQuery);

        return dto;
    }

}
