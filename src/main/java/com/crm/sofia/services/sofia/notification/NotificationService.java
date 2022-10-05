package com.crm.sofia.services.sofia.notification;

import com.crm.sofia.dto.sofia.notification.NotificationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter) {

        emitter.onCompletion(() -> {
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });

        emitters.add(emitter);

        return emitter;
    }

    public void send(NotificationDTO notificationDTO) {

        List<SseEmitter> failedEmitters = new ArrayList<>();

        this.emitters.forEach(emitter->{
            try {
                emitter.send(SseEmitter
                        .event()
                        .name("user6")
                        .data(notificationDTO));
            } catch (Exception e) {
                emitter.completeWithError(e);
                failedEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(failedEmitters);
    }
}
