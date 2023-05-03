package com.crm.sofia.services.notification;

import com.crm.sofia.dto.notification.HtmlTemplateNotificationParamsDTO;
import com.crm.sofia.dto.notification.NotificationDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.notification.NotificationMapper;
import com.crm.sofia.model.notification.Notification;
import com.crm.sofia.repository.notification.NotificationRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.html_template.HtmlTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final JWTService jwtService;

    private final HtmlTemplateService htmlTemplateService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, JWTService jwtService, HtmlTemplateService htmlTemplateService, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.jwtService = jwtService;
        this.htmlTemplateService = htmlTemplateService;
        this.messagingTemplate = messagingTemplate;
    }

    public NotificationDTO getObject(String id) {
        Notification entity = this.notificationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist"));

        NotificationDTO dto = this.notificationMapper.map(entity);

        return dto;
    }

    public List<NotificationDTO> getObject() {
        List<NotificationDTO> dtos = this.notificationRepository.findAllByReceiverId(jwtService.getUserId());
        return dtos;
    }

    public List<NotificationDTO> getUnreadNotifications() {
        List<NotificationDTO> dtos = this.notificationRepository.findUnreadByReceiverId(jwtService.getUserId());
        return dtos;
    }

    public void postObject(NotificationDTO objectDto) {
        Notification object = notificationMapper.map(objectDto);
        if (objectDto.getId() == null) {
            object.setCreatedOn(Instant.now());
            object.setCreatedBy(jwtService.getUserId());
        }
        object.setModifiedOn(Instant.now());
        object.setModifiedBy(jwtService.getUserId());

        object.setSenderId(jwtService.getUserId());
        object.setStatus("unread");

        notificationRepository.save(object);
    }

    public void deleteObject(String id) {
        Notification entity = notificationRepository.findById(id).orElseThrow(() -> new DoesNotExistException("Object Does Not Exist"));

        notificationRepository.deleteById(entity.getId());
    }

    public void createHtmlTemplateNotification(HtmlTemplateNotificationParamsDTO params) {

        String title = htmlTemplateService.createHtmlTitle(params.getHtmlReportId(), params.getHtmlReportSelectionId());
        String message = htmlTemplateService.createHtmlTemplate(params.getHtmlReportId(), params.getHtmlReportSelectionId());

        params.getReceiverIds().forEach(receiverId -> {
            Notification notification = new Notification();
            notification.setTitle(title);
            notification.setMessage(message);
            notification.setCreatedOn(Instant.now());
            notification.setCreatedBy(jwtService.getUserId());
            notification.setModifiedOn(Instant.now());
            notification.setModifiedBy(jwtService.getUserId());
            notification.setSenderId(jwtService.getUserId());
            notification.setReceiverId(receiverId);
            notification.setStatus("unread");

            Notification createdNotification = notificationRepository.save(notification);

            NotificationDTO notificationDTO = this.notificationMapper.map(createdNotification);

            messagingTemplate.convertAndSend("/topic/user-notifications/" + receiverId,
                    Map.of("id", createdNotification.getId(), "title", createdNotification.getTitle()));
        });
    }

    public void makeRead(String id) {
        this.notificationRepository.makeRead(id);
    }
}
