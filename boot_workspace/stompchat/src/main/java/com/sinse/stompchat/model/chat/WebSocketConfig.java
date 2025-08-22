package com.sinse.stompchat.model.chat;

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
        registry.enableSimpleBroker("/topic");

        //클라이언트에서 서버로 요청을 보낼 때에는 무조건 접두어에 /app로 시작해야함을 명시.
        //마치 스프링 레거시에서 web.xml 에 context root path를 /admin , /shop 으로 적는 느낌.
        registry.setApplicationDestinationPrefixes("/app");

        //브로드캐스팅이 아닌 , 1:! 메시징 처리에서 사용할 사용자의 prefix
        //클라이어트는 무조건 /user/.,../처럼 /user로 시작
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //클라이언트의 서버 접속 엔드포인트 지정
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");

    }
}
