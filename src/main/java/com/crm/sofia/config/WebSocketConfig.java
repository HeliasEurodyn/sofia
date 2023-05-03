package com.crm.sofia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/sockjs").setAllowedOrigins("*").withSockJS();
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public String greeting(Message message) throws Exception {
//       // String name = new JSONObject(message.getPayload().toString()).getString("name");
//        System.out.println(message.getPayload().toString());
//        return message.getPayload().toString();
//    }


}
