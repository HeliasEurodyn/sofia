package com.crm.sofia.services.sofia.notification;

import com.crm.sofia.dto.sofia.notification.NotificationDTO;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    final
    JWTService jwtService;
    private final ConcurrentHashMap<String,SseEmitter> emitters = new ConcurrentHashMap();
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);


    public NotificationService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostConstruct
    public void init() {
        executor.scheduleWithFixedDelay(keepAlive, 40, 40, TimeUnit.SECONDS);
    }

    public SseEmitter add(SseEmitter emitter) {

        String userId = this.jwtService.getUserId();

        emitter.onCompletion(() -> {
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
        });

        emitters.put(userId,emitter);

        return emitter;
    }

    public void unsubscribe(String id) {
        if(!id.isEmpty() && id !=null && !this.emitters.isEmpty()){
            Optional.ofNullable(this.emitters.get(id)).ifPresent(emitter -> emitter.complete());
        }

    }

    public void send(NotificationDTO notificationDTO) {

       HashMap<String,SseEmitter> failedEmitters = new HashMap<>();

     this.emitters
             .entrySet()
             .stream()
             .filter(emitterEntry-> emitterEntry.getKey().equals("da7a00e9-3b19-454d-b72b-8646d6eae678"))
             .forEach(emitterEntry -> {
                 try {
                     emitterEntry.getValue().send(SseEmitter
                             .event()
                             .name("da7a00e9-3b19-454d-b72b-8646d6eae678")
                             .data(notificationDTO));
                 } catch (Exception e) {
                     emitterEntry.getValue().completeWithError(e);
                     failedEmitters.put(emitterEntry.getKey(),emitterEntry.getValue());
                 }
             });
      this.emitters.entrySet().removeAll(failedEmitters.entrySet());
    }

    Runnable keepAlive = () -> {

        HashMap<String,SseEmitter> failedEmitters = new HashMap<>();

        this.emitters.forEach((userId,emitter) -> {
            try {
                emitter.send(SseEmitter
                        .event()
                        .name("keepAlive")
                        .data("keepAlive"));
            } catch (Exception e) {
                if(this.emitters.contains(userId)){
                    emitter.completeWithError(e);
                    failedEmitters.put(userId,emitter);
                }
            }
        });

        this.emitters.entrySet().removeAll(failedEmitters.entrySet());

    };


}
