package com.crm.sofia.services.sofia.notification;

import com.crm.sofia.dto.sofia.notification.NotificationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class NotificationService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private List<SseEmitter> failedEmitters = new ArrayList<>();

    @PostConstruct
    public void init() {
       executor.scheduleWithFixedDelay(keepAlive,40,40, TimeUnit.SECONDS);
    }

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

    public void unsubscribe() {

    }

    public void send(NotificationDTO notificationDTO) {

        failedEmitters.clear();

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

    Runnable keepAlive = () -> {

            failedEmitters.clear();

            this.emitters.forEach(emitter->{
                try {
                    emitter.send(SseEmitter
                            .event()
                            .name("keepAlive")
                            .data("keepAlive"));
                } catch (Exception e) {
                    emitter.completeWithError(e);
                    failedEmitters.add(emitter);
                }
            });

            this.emitters.removeAll(failedEmitters);

        };


}
