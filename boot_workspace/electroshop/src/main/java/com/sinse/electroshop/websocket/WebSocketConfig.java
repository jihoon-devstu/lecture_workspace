package com.sinse.electroshop.websocket;

import com.sinse.electroshop.websocket.interceptor.HttpSessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //서버가 클라이언트에게 브로드케스트를 할 구독 메시지 접두어
        registry.enableSimpleBroker("/topic");
        
        //클라이언트가 서버롷 요청을 보낼 때는 무조건 붙여야 하는 컨텍스트 루트
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionInterceptor());

    }
}
