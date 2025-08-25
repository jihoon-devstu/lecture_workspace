package com.sinse.electroshop.controller.dto;

import lombok.Data;

//데이터베이스 컬럼을 표현한 것이 아닌 , 오직 클라이언트가 전송한 파라미터를 받기 위해.
@Data
public class StoreDto {
    private int store_id;
    private String id;
    private String pwd;
    private String store_name;
}
