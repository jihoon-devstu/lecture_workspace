package com.sinse.stompchat.controller;

import com.sinse.stompchat.dto.ChatMessage;
import com.sinse.stompchat.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
public class ChatController {
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();
    private Map<String , ChatRoom> roomStorage = new ConcurrentHashMap<>() ;

    //favicon 처리
    //1. static 디렉토리에 실제로 favicon 이미지를 보유
    //2. 컨트롤러에 요청을 처리하되 , 이미지를 반환하지 않고 void 처리.
    @GetMapping("/favicon.ico")
    @ResponseBody
    public void favicon() {}

    /*클라이언트의 접속 요청 처리*/
    @MessageMapping("/connect")
    @SendTo("/topic/users")
    //이 메서드 실행의 결과는 내부적으로 ObjectMapper에 의한 Json문자열이다.
    //또한 이 메서드 실행 결과를 받을 대상 클라이언트는 개발자가 직접 반복문을 돌리지 않아도  SendTo 어노테이션이 /topci/users 채널을 구독한 클라이언트에게 브로드캐스팅을 수행해 준다.
    //고전적인 방식의 웹 소켓 에서는 클라이언트가 전송한 프로토콜에 의해 if문 사용.
    public Set<String> connect(ChatMessage chatMessage) {
        log.debug(chatMessage.getSender()+"클라이언트의 접속 요청 받음");
        connectedUsers.add(chatMessage.getSender());
        return connectedUsers;
    }

    //클라이언트에 메세지 전송 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/room.create")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> createRoom(ChatMessage chatMessage) {
        ChatRoom room = new ChatRoom();
        String roomId = UUID.randomUUID().toString();
        room.setRoomId(roomId); //고유 ID
        room.setRoomName(chatMessage.getContent());

        roomStorage.put(roomId, room);
        return  roomStorage.values();
    }

    @MessageMapping("/room.enter")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> enterRoom(ChatMessage chatMessage) {
        //방 검색하여 , 발견된 방의 Set에 유저명 넣기
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());

        if(chatRoom!=null){
            chatRoom.getUsers().add(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    @MessageMapping("/room.leave")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> leaveRoom(ChatMessage chatMessage) {
        //방 검색하여 , 발견된 방의 Set 에서 유저명 빼기
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());

        if(chatRoom!=null){
            chatRoom.getUsers().remove(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    @MessageMapping("/room.list")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> roomList(ChatMessage chatMessage) {
        //방 검색하여 , 발견된 방의 Set 에서 유저명 빼기
        return roomStorage.values();
    }

    @MessageMapping("/disconnect")
    @SendTo("/topic/users")
    public Set<String> disconnect(ChatMessage chatMessage) {
        connectedUsers.remove(chatMessage.getSender());
        return connectedUsers;
    }


}
