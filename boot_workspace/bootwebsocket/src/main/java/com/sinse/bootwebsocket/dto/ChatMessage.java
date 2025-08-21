package com.sinse.bootwebsocket.dto;

import lombok.Data;

@Data
public class ChatMessage {

    /*
        CONNECT : 접속
        DISCONNECT :  접속 해제
        MESSAGE : 채팅
        ROOM_CREATE : 방 만들기
        ROOM_LIST : 방 목록
        ROOM_ENTER : 방 들어가기
        ROOM_LEAVE : 방 나가기


    */
    private String type;
    private String sender;
    private String content;
    private String roomId;
}
