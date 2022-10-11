package com.crm.sofia.controllers.sofia.sse_notification;

import com.crm.sofia.dto.sofia.sse_notification.SseNotificationResponseDTO;
import com.crm.sofia.services.sofia.sse_notification.SseNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/sse-notification")
public class SseNotificationController {


/*    private final SseNotificationService sseNotificationService;


    public SseNotificationController(SseNotificationService sseNotificationService) {
        this.sseNotificationService = sseNotificationService;
    }


    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        SseEmitter emitter = sseNotificationService.add();
        return emitter;
    }

    @GetMapping("/unsubscribe")
    public  void unsubscribe(@RequestParam("id") String id) {
        sseNotificationService.unsubscribe(id);
    }

    @PostMapping("/send")
    public ResponseEntity<SseNotificationResponseDTO> send(@RequestBody SseNotificationResponseDTO sseNotificationResponseDTO) {
        sseNotificationService.send(sseNotificationResponseDTO);
        return ResponseEntity.ok(sseNotificationResponseDTO);
    }*/
}
