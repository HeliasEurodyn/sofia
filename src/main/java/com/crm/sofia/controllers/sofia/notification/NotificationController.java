package com.crm.sofia.controllers.sofia.notification;

import com.crm.sofia.dto.sofia.notification.NotificationDTO;
import com.crm.sofia.services.sofia.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/notification")
public class NotificationController {


    private final NotificationService notificationService;


    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        SseEmitter emitter = notificationService.add();
        return emitter;
    }

    @GetMapping("/unsubscribe")
    public  void unsubscribe(@RequestParam("id") String id) {
        notificationService.unsubscribe(id);
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationDTO> send(@RequestBody NotificationDTO notificationDTO) {
        notificationService.send(notificationDTO);
        return ResponseEntity.ok(notificationDTO);
    }
}
