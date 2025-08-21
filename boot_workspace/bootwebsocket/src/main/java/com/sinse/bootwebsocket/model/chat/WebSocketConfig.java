package com.sinse.bootwebsocket.model.chat;

//Spring 에서 지원하는 WebSocket 은 여기서 Endpoint 지정할 수 있고 ,
//또한 클라이언트의 요청을 처리하는 객체를 여기에서 등록해야 한다.

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor //매개 변수가 있는 생성자를 자동 생성해주는 롬복의 기능.
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/ws")
                .setAllowedOrigins("*");
    }
}
