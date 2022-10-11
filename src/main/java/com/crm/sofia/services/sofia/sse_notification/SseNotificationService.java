package com.crm.sofia.services.sofia.sse_notification;

import com.crm.sofia.dto.sofia.sse_notification.SseNotificationResponseDTO;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SseNotificationService {

    final
    JWTService jwtService;
    private final ConcurrentHashMap<String,SseEmitter> emitters = new ConcurrentHashMap();
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);


    public SseNotificationService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostConstruct
    public void init() {
        executor.scheduleWithFixedDelay(keepAlive, 40, 40, TimeUnit.SECONDS);
    }

    public SseEmitter add() {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        String userId = this.jwtService.getUserId();

        emitter.onCompletion(() -> {
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
        });

        emitters.put(userId + "~" + new Random().nextLong(),emitter);

        return emitter;
    }

    public void unsubscribe(String id) {
        if(!id.isEmpty() && id !=null && !this.emitters.isEmpty()){
            Optional.ofNullable(this.emitters.get(id)).ifPresent(emitter -> emitter.complete());
        }

    }

    public void send(SseNotificationResponseDTO sseNotificationResponseDTO) {

       HashMap<String,SseEmitter> failedEmitters = new HashMap<>();

        if(sseNotificationResponseDTO.getUserToSendId()!=null && sseNotificationResponseDTO.getMessage()!=null){
            this.emitters
                    .entrySet()
                    .stream()
                    .filter(emitterEntry-> emitterEntry.getKey().split("~")[0].equals(sseNotificationResponseDTO.getUserToSendId()))
                    .forEach(emitterEntry -> {
                        try {
                            emitterEntry.getValue().send(SseEmitter
                                    .event()
                                    .name(sseNotificationResponseDTO.getUserToSendId())
                                    .data(sseNotificationResponseDTO.getMessage()));
                        } catch (Exception e) {
                            emitterEntry.getValue().completeWithError(e);
                            failedEmitters.put(emitterEntry.getKey(),emitterEntry.getValue());
                        }
                    });
        }


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
