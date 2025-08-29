package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.ChatMessage;
import com.sinse.electroshop.controller.dto.ChatRoom;
import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.domain.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
Spring의 STOMP 를 이용하면 웹 소켓을 일반 컨트롤러로 제어할 수 있다.
마치 웹 요청을 처리하듯...
*/

@Slf4j
@Controller
public class ChatController {

    //서버에 접속한 모든 유저 목록
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //서버에 존재하는 모든 방 목록 (상품 수와 일치)
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    //해당 방에 참여한 사용자 목록


    /*---------------------------------------------------------------------------
        접속 요청 처리
        접속과 동시에 해당 상품과 관련된 방 하나를 선택하고 , 그 방에 참여한 고객목록 반환
    -----------------------------------------------------------------------------*/
    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public Set<String> connect(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        ChatRoom chatRoom = null;
        int product_id = Integer.parseInt(message.getContent());

        //SimpMessageHeaderAccessor 객체를 이용하면 WebSocket의 Session에 들어있는 정보 추출
        if(headerAccessor.getSessionAttributes().get("member") != null) {
            Member member = (Member) headerAccessor.getSessionAttributes().get("member");
            //일반 회원은 개설된 방에 참여하면 됨
            for(ChatRoom room:roomStorage.values()){
                if(room.getProductId()==product_id){
                    chatRoom = room;
                    break;
                }
            }
            log.debug("httpsession에서 받아온 멤버 정보는"+ member.getName());
            log.debug("클라이언트 서버에 접속함" + message.getContent());
            chatRoom.getCustomers().add(member.getName());
            connectedUsers.add(member.getName());


        }else if(headerAccessor.getSessionAttributes().get("store") != null) {
            Store store = (Store)  headerAccessor.getSessionAttributes().get("store");
            log.debug("httpsession에서 받아온 멤버 정보는"+ store.getStoreName());
            log.debug("클라이언트 서버에 접속함" + message.getContent());


            //룸을 추가하기 전에 중복여부 판단하기.
            boolean exist = false; //중복여부 판단 기준
            for(ChatRoom room:roomStorage.values()){
                if(room.getProductId()==product_id){
                    exist = true;
                    chatRoom = room;
                    break;
                }
            }

            //중복이 없을때에만 방 추가
            if(!exist){
                chatRoom = new ChatRoom();
                chatRoom.setRoomId(UUID.randomUUID().toString()); //방의 구분 고유 Id
                chatRoom.setCustomers(new ConcurrentHashMap<>().newKeySet());
                chatRoom.setProductId(product_id);
                chatRoom.getCustomers().add(store.getBusinessId());
                roomStorage.put(store.getStoreName(), chatRoom);
            }


            //방 참여하기.
            chatRoom.getCustomers().add(store.getBusinessId());
            connectedUsers.add(store.getBusinessId());

        }

        return chatRoom.getCustomers();
    }

    //메시지 요청 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        log.debug(message.getSender()+" 가 전송한 메시지 " + message.getContent());
        return message;
    }


}
