package com.sinse.chatroomapp.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.dto.ChatResponse;
import com.sinse.chatroomapp.dto.EnterRoomResponse;
import com.sinse.chatroomapp.dto.Room;
import com.sinse.chatroomapp.dto.CreateRoomResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
            CreateRoomResponse createRoomResponse = new CreateRoomResponse();
            createRoomResponse.setResponseType("createRoom");

            //회원 정보 채우기
            Member obj = new Member();
            obj.setId(loginMember.getId());
            obj.setEmail(loginMember.getEmail());
            obj.setName(loginMember.getName());
            memberList.add(obj);

            createRoomResponse.setMemberList(memberList);
            createRoomResponse.setRoomList(roomList);
            String json = objectMapper.writeValueAsString(createRoomResponse);

            for (Session s : userList) {
                s.getAsyncRemote().sendText(json);
            }
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

                CreateRoomResponse createRoomResponse = new CreateRoomResponse();
                createRoomResponse.setResponseType("createRoom");
                createRoomResponse.setMemberList(memberList);
                createRoomResponse.setRoomList(roomList);
                try {
                    String jsonResponse = objectMapper.writeValueAsString(createRoomResponse);
                    log.debug("JSON 변환 성공: " + jsonResponse);
                    for (Session s : userList) {
                        s.getAsyncRemote().sendText(jsonResponse);
                    }
                    log.debug("전체 사용자에게 방 생성 정보 브로드캐스팅 성공");

                } catch (Exception e) {
                    log.error("JSON 변환 또는 메시지 전송 중 심각한 에러 발생!!!", e);
                }
            }

        }else if (requestType.equals("enterRoom")) {
            log.debug("방 입장 처리 요청");
            /**
             * 1)  요청한 클라이언트 선택한 방에 밀어넣기 !!
             *      - 넘겨받은 uuid를 이용하여 방 선택
             *      - 해당 room이 보유하고있는 Set<Member> users 에 클라이언트를 참여자로 등록 (중복을 피하며...)
             * */
            String uuid = jsonNode.get("uuid").asText();

            //클라이언트가 전송한 uui를 이용하여 모든 방을 탐색 한 후,
            //uuid가 일치하는 방을 선택.
            Room room = null;
            for(Room r:roomList){
                if(r.getUUID().equals(uuid)){
                    room = r;
                    break;
                }
            }

            /**
             * 위 방식이 전통적 방식.
             * 새롭게 나온 방식인 선언적 프로그래밍 방식으로도 위 작업을 진행할 수 있다.
             *
             * 선언적 프로그래밍 : 데이터를 다루는 컬렉션에 최적화.
             * */

/*            roomList.stream()
                    .filter(r -> r.getUUID().equals(uuid)) //조건에 맞는 요소만 추리자 !!
                    .findFirst() //조건에 맞는 첫번 째 요소를 반환
                    .orElse(null);*/
            if(room!=null){
                Member member = (Member)session.getUserProperties().get("member");
                boolean exists=false;
                for(Member m : room.getUsers()){
                    if(m.getId().equals(member.getId())){
                        exists=true;
                        break;
                    }
                }
                Member obj = null; //이 멤버사 곧 클라이언트에게 전송될 예정이므로 , 보안상 중요한 부분은 제외 시키기 위해 별도의 member 선언
                //룸에 등록되어 있지 않다면 ...
                if(exists == false){
                    obj = new Member();
                    obj.setId(member.getId());
                    obj.setEmail(member.getEmail());
                    obj.setName(member.getName());
                    room.getUsers().add(obj); //채팅방 참여자로 등록 !!
                }
                /**
                 * 응답 정보 만들기 !!!
                 * */
                EnterRoomResponse enterRoomResponse = new EnterRoomResponse();
                enterRoomResponse.setResponseType("enterRoom");
                enterRoomResponse.setRoom(room);
                session.getAsyncRemote().sendText(objectMapper.writeValueAsString(enterRoomResponse));

                CreateRoomResponse response = new CreateRoomResponse();
                response.setResponseType("createRoom");
                response.setMemberList(memberList);
                response.setRoomList(roomList); // 사용자가 추가된 최신 roomList 정보

                String jsonResponse = objectMapper.writeValueAsString(response);
                for (Session s : userList) {
                    s.getAsyncRemote().sendText(jsonResponse);
                }
            }
        }else if (requestType.equals("leaveRoom")) {

        }else if (requestType.equals("chat")) {
            String senderId = jsonNode.get("senderId").asText();
            String senderName = jsonNode.get("senderName").asText();
            String data = jsonNode.get("data").asText();
            String uuid = jsonNode.get("uuid").asText();

            /**
             *  같은 방에 있는 유저들에게 브로드 캐스팅
             * */


            /**
             * 클라이언트가 전송한 UUID를 이용하여 , 서버에 존재하는 여러 채팅방 중 한 방을 선택하자
             *
             * */
            Room room = null;
            for(Room r : roomList){
                if(uuid.equals(r.getUUID())){
                    room = r;
                    break;
                }
            }

            /**
             * Room에 들어있는 대화 참여자들의 정보를 이용하여 , Session 을 보유한 userList와 비교한 후
             * 대화 참여자의 Session이 발견되면 , 메시지를 보내자
             * */

            //메세지 보낼 때 프로토콜 정의하기.

            ChatResponse chatResponse = new ChatResponse();
            chatResponse.setResponseType("chat");
            chatResponse.setMessageId(UUID.randomUUID().toString());
            chatResponse.setSenderId(senderId);
            chatResponse.setData(data);
            chatResponse.setSenderName(senderName);
            chatResponse.setUuid(uuid);

            String sendJson = objectMapper.writeValueAsString(chatResponse);

            for(Session ss : userList){
                for(Member member:room.getUsers()){ //지정된 방에 참여한 참여자를 대상으로...
                    Member obj = (Member)ss.getUserProperties().get("member");
                    if(obj.getId().equals(member.getId())){
                        ss.getAsyncRemote().sendText(sendJson);
                    }
                }
            }

        }

    }



    @OnClose
    public void onClose(Session session) throws Exception {
        //3가지 목록에서 사용자 제거
        Member member = (Member)session.getUserProperties().get("member");

        memberList.remove(member);
        userList.remove(member);

        //현재 접속자가 참여하고 있었던 그 방에서 빼야 한다.
        //roomList에서 Room을 선택하여 해당 Room 이 보유한 Set에서 현재 Member 제거
        Room room = null;
        Member mr = null;
        for(Room r:roomList){
            for(Member m:r.getUsers()){
                if(m.getId().equals(member.getId())){
                    room = r;
                    mr = m;
                    break;
                }
            }
        }

        //클라이언트에게 전송할 정보 구성 후 보내기
        //응답 정보 구성하여 전송
        CreateRoomResponse response = new CreateRoomResponse();
        response.setResponseType("close");
        response.setMemberList(memberList);
        response.setRoomList(roomList);

        String jsonResponse = objectMapper.writeValueAsString(response);
        session.getAsyncRemote().sendText(jsonResponse);


    }

}
