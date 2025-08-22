package com.sinse.bootwebsocket.model.chat;

/*
    Spring에서 웹 소켓 Server Endpoint 를 다루는 객체는 WebSocketHandler만 있는게 아님.
    [TextWebSocketHandler]
    -> 다루고자 하는 데이터가 Text일 경우 효율적.
    -> 인터페이스가 아니다 보니 , 사용할 필요 없는 메서드를 재정의할 필요 없다. 즉 필요한 것만 골라서 재정의.

 */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.bootwebsocket.dto.ChatMessage;
import com.sinse.bootwebsocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatTextWebSocketHandler extends TextWebSocketHandler {

    //java와 - json 문자열과의 변환을 자동으로 해주는 객체
    private ObjectMapper objectMapper = new ObjectMapper();

    //현재 서버에 연결되어있는 모든 클라이언트 세션의 집합
    private Set<WebSocketSession> sessions = new ConcurrentHashMap<>().newKeySet();

    //현재 서버에 접속되어 있는 모든 클라이언트 아이디 집합 (클라이언트 전송용)
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //전체 방 목록 집합
    private Map<String , ChatRoom> roomStorage = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    //모든 클라이언트가 동시에 알아야할 정보를 전송할 브로드캐스트 메서드 정의
    //매개변수가 Object인 이유는 ObjectMapper에게 Json 문자열로 변환을 맡길 데이터 형식이 결정되어있지 않기 때문에...
    private void broadcast(String destination, Object data) throws Exception {
        String payload = objectMapper.writeValueAsString(
          Map.of("destination", destination, "body", data)
        );

        for(WebSocketSession session : sessions) {
            if(session.isOpen()) {
                session.sendMessage(new TextMessage(payload));
            }
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //클라이언트는 자바가 이해할 수 없는 json 문자열 형태로 메세지를 전송하므로
        //서버측에서는 해석이 필요하다.
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        //클라이언트 요청 분기
        switch (chatMessage.getType()) {
            case "CONNECT"->{
                //주의 : 클라이언트가 접속하자마자 메시지 전송을 보냈을 때 ,
                //afterconnection 이 아닌 , handle Message가 동작함 !!!
                connectedUsers.add(chatMessage.getSender());
                broadcast("/users", connectedUsers);
            }
            case "DISCONNECT"->{
                connectedUsers.remove(chatMessage.getSender());
                broadcast("/users", connectedUsers);
            }
            case "MESSAGE"->{
                broadcast("/messages", chatMessage );
            }
            case "ROOM_CREATE"->{
                ChatRoom room = new ChatRoom();
                String uuid = UUID.randomUUID().toString();
                room.setRoomId(uuid);
                room.setRoomName(chatMessage.getContent());
                roomStorage.put(uuid, room);
                broadcast("/rooms", room);

            }
            case "ROOM_LIST"->{
                broadcast("/rooms", roomStorage.values());
            }
            case "ROOM_ENTER"->{
                // 방을 검색 하여 검색된 방의 Set에 user를 추가.

            }
            case "ROOM_LEAVE"->{

            }
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}

