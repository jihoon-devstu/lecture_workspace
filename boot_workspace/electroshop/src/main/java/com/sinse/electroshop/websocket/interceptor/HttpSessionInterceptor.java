package com.sinse.electroshop.websocket.interceptor;

import com.sinse.electroshop.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

//아래의 클래스는 WebSocket 연결과정(Handshake) 에서 HttpSession 정보를 webSocket 세션 속성으로
//옮겨놓기 위한 객체
@Slf4j
public class HttpSessionInterceptor implements HandshakeInterceptor {

    //websocket 핸드세이크가 시작되기 전에 호출되는 메서드 (접속할 때)
    //이 타이밍을 놓치지 말고 , httpsession에 들어있는 값을 WebSocket의 Session에 옮겨 심기.
    //주의) 4번째 매개변수인 attributes가 바로 WebSocket 세션의 attributes 임.
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        //HttpSession 에서 Member 객체 꺼내기.
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

        //만일 세션이 없을 경우 , 새로 생성하지 마 ! getSession(false);
        HttpSession httpSession = servletRequest.getServletRequest().getSession(false);

        if(httpSession != null){
            Member member = (Member) httpSession.getAttribute("member");
            attributes.put("member", member);
            log.debug("핸드셰이크 시점에 추출한 회원의 이름은" + member.getId());
        }else {
            log.warn("HTTP 세션이 존재하지 않습니다. 인터셉터 로직이 실행되지 않습니다.");
        }

        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
