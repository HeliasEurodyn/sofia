package com.crm.sofia.controllers.notification;

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

        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        notificationService.add(sseEmitter);
        return sseEmitter;
    }

    @GetMapping("/unsubscribe")
    public  void unsubscribe() {
        notificationService.unsubscribe();
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationDTO> send(@RequestBody NotificationDTO notificationDTO) {
        notificationService.send(notificationDTO);
        return ResponseEntity.ok(notificationDTO);
    }
}
