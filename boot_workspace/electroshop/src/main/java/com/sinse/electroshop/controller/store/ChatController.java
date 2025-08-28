package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.ChatMessage;
import com.sinse.electroshop.controller.dto.ChatRoom;
import com.sinse.electroshop.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;
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
    public Set<String> connect(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {

        //SimpMessageHeaderAccessor 객체를 이용하면 WebSocket의 Session에 들어있는 정보 추출
        Member member = (Member) headerAccessor.getSessionAttributes().get("member");

        log.debug("httpsession에서 받아온 멤버 정보는"+ member.getName());
        log.debug("클라이언트 서버에 접속함" + message.getContent());
        
        //STOMP 기반으로 HttpSession을 꺼내려면 인터셉터 객체 구현 및 등록해야함.
        //1) 내가 참여하지 않았을 경우 , 이 상품과 관련된 방에 참여하기
        //1-1가장 먼저 connected users에 내 정보 집어넣기 (String이므로 member.getId로)
        //1-2. product_id를 가지고 , 만들어진 chatRoom 순회하며 채팅방이 만들어져있는지 없는지 검사 후 없으면 만들기
        //1-3. 만들어진 채팅방에 내가 참여하지 않았을 경우 , 받아온 member를 가지고 해당 채팅방에 집어넣기.


        
        //2) 내가 참여한 방과 같은 방에 있는 유저들 목록을 얻어와 @SendTo 로 보내기...
        //2-1 내가 참여한 채팅방의 UUID를 돌아다니며 내가 속한 채팅방 특정.
        //2-2 해당 채팅방의 Set안에 포함된 유저들 목록을 불러오기
        //2-3 유저들에게 sendTo로 보내주기.
        return null;
    }




}
