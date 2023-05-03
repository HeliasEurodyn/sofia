package com.crm.sofia.controllers.notification;

import com.crm.sofia.dto.language.LanguageDTO;
import com.crm.sofia.dto.notification.HtmlTemplateNotificationParamsDTO;
import com.crm.sofia.dto.notification.NotificationDTO;
import com.crm.sofia.services.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    List<NotificationDTO> getObject() {
        return notificationService.getObject();
    }

    @GetMapping(path = "/unread")
    List<NotificationDTO> getUnreadNotifications() {
        return notificationService.getUnreadNotifications();
    }

    @GetMapping(path = "/by-id")
    NotificationDTO getObject(@RequestParam("id") String id) {
        return notificationService.getObject(id);
    }

    @PostMapping
    public void postObject(@RequestBody NotificationDTO notificationDTO) {
        notificationService.postObject(notificationDTO);
    }

    @PostMapping(path = "/html-template")
    public void createHtmlTemplateNotification(
            @RequestBody HtmlTemplateNotificationParamsDTO params)  {
        notificationService.createHtmlTemplateNotification(params);
    }

    @PutMapping(path = "/read")
    public void makeRead(@RequestParam("id") String id)  {
        notificationService.makeRead(id);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        notificationService.deleteObject(id);
    }

}
