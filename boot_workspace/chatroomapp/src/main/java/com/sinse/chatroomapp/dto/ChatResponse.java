package com.sinse.chatroomapp.dto;

import lombok.Data;

@Data
public class ChatResponse {
    private String responseType;
    private String senderId;
    private String senderName;
    private String data;
    private String uuid;
    private String messageId;
}
