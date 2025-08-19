package com.sinse.chatroomapp.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.dto.Room;
import com.sinse.chatroomapp.dto.RoomResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@ServerEndpoint(value="/chat/multi", configurator = HttpSessionConfigurator.class)
@Component
public class ChatEndpoint {
    //잭슨 객체 제이슨 문자열로 파싱 해주는 객체
    private static ObjectMapper objectMapper = new ObjectMapper();

    //서버측에서 필요한 접속자 명단
    private static Set<Session> userList = new HashSet<>();

    //클라이언트에게 전송할 접속자 명단
    private static Set<Member> memberList = new HashSet<>();

    //클라이언틍게게 전송할 룸 정보를 저장할 명단
    private static Set<Room> roomList = new HashSet<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws Exception {
        HttpSession httpSession=(HttpSession)config.getUserProperties().get(HttpSession.class.getName());

        if(httpSession!=null){
            Member loginMember = (Member)httpSession.getAttribute("member");

            //어차피 클라이언트 브라우저에서는 접속자 명단만 필요하므로 , 서버측에서 너무나 예민한 정보는
            //클라이언트에게 보내줄 필요가 없다. 따라서 Member 모델에서 id만 추출해보자.

            session.getUserProperties().put("member",loginMember);
            userList.add(session); //접속자 명단에 현재 웹 소켓 세션 추가

            //접속한 클라이언트가 알아야할 정보 전송 (userList , roomList 등)
            //단 , 클라이언트와의 통신은 정해진 프로토콜을 지켜 보내자

            //응답 정보 만들기
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setResponseType("createRoom");

            //회원 정보 채우기
            Member obj = new Member();
            obj.setId(loginMember.getId());
            obj.setEmail(loginMember.getEmail());
            obj.setName(loginMember.getName());
            memberList.add(obj);

            roomResponse.setMemberList(memberList);
            String json = objectMapper.writeValueAsString(roomResponse);

            session.getAsyncRemote().sendText(json);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.debug("클라이언트 메시지 : " + message);
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();

        if (requestType.equals("createRoom")) {
            log.debug("방 만들어 줄게");

            String userId =  jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();
            // 로그인 시 사용된 HttpSession에 들어있는 회원정보와 , 웹 소켓을 통해 전달된 회원정보를
            // 비교하여 일치하는지 검증 해 보자 !
            Member member = (Member)session.getUserProperties().get("member");
            if(!member.getId().equals(userId)){
                //클라이언트에게 에러 전송
            }else{
                //방 고유 식별자
                UUID uuid = UUID.randomUUID();
                Room room = new Room();
                room.setUUID(uuid.toString());
                room.setMaster(userId);
                room.setRoomName(roomName);

                Set users = new HashSet<>();

                Member obj = new Member();
                obj.setId(member.getId());
                obj.setEmail(member.getEmail());
                obj.setName(member.getName());
                
                users.add(member); //방을 개설한 주인을 참여자로 등록
                /**
                 * 클라이언트에게 전송할 응답 프로토콜
                 *
                 * */
                room.setUsers(users);
                roomList.add(room);

                RoomResponse roomResponse = new RoomResponse();
                roomResponse.setResponseType("createRoom");
                roomResponse.setMemberList(memberList);
                roomResponse.setRoomList(roomList);

                session.getAsyncRemote().sendText(objectMapper.writeValueAsString(roomResponse));
            }

        }else if (requestType.equals("joinRoom")) {

        }else if (requestType.equals("leaveRoom")) {

        }else if (requestType.equals("chat")) {

        }

    }

}
