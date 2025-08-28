package com.sinse.electroshop.controller.dto;

import lombok.Data;

@Data
public class ChatMessage {

    private String type; //클라이언트의 요청을 구분하기 위한 구분 값
    private String sender; //메세지 발신자
    private String content; //메시지 내용 (무엇이 될지는 알 수 없다.)
    private String roomId; //메시지가 전달되고 있는 방

}
