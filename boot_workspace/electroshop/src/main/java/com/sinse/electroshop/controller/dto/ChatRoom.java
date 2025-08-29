package com.sinse.electroshop.controller.dto;

import lombok.Data;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 채팅방 정보를 담는 DTO 객체
 * 1) 채팅방의 고유 값
 * 2) 어떤 상품에 대한 채팅방인지
 * 3) 채팅방의 참여자 목록 Set
 * */
@Data
public class ChatRoom {

    private String roomId; //채팅방 고유값
    private int productId; //어떤 상품에 대한 채팅방인지
    private Set<String> customers; //채팅방 참여 고객들
}
