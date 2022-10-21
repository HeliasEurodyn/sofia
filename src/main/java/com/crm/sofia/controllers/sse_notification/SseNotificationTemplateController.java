package com.crm.sofia.controllers.sse_notification;

import com.crm.sofia.dto.sse_notification.SseNotificationDTO;
import com.crm.sofia.services.sse_notification.SseNotificationTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/sse-notification-template")
public class SseNotificationTemplateController {

    private final SseNotificationTemplateService sseNotificationTemplateService;

    public SseNotificationTemplateController(SseNotificationTemplateService sseNotificationTemplateService) {
        this.sseNotificationTemplateService = sseNotificationTemplateService;
    }


    @GetMapping
    List<SseNotificationDTO> getObject() {
        return sseNotificationTemplateService.getObject();
    }

    @GetMapping(path = "/by-id")
    SseNotificationDTO getObject(@RequestParam("id") String id) {
        return sseNotificationTemplateService.getObject(id);
    }

    @PostMapping
    public SseNotificationDTO postObject(@RequestBody SseNotificationDTO sseNotificationDTO) throws IOException {
        return sseNotificationTemplateService.postObject(sseNotificationDTO);
    }

    @PutMapping
    public SseNotificationDTO putObject(@RequestBody SseNotificationDTO sseNotificationDTO) {
        return sseNotificationTemplateService.postObject(sseNotificationDTO);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        sseNotificationTemplateService.deleteObject(id);
    }

}
