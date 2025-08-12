package com.sinse.foodapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private List<Body> body;
    private Header header;

    //Body를 정의 (내부 클래스로 정의, 왜? 분산시키면 유지관리가 복잡할 수 있기 때문에)
    @Data
    public static class Body {
        @JsonProperty("SIGUN")
        private String sigun; // 관할 행정 구역

        @JsonProperty("LO")
        private double lo; // 경도

        @JsonProperty("MNMNU")
        private String mnmnu; // 메인메뉴

        @JsonProperty("SE")
        private String se; // 간단 설명

        @JsonProperty("CMPNM")
        private String cmpnm; // 상호명

        @JsonProperty("MENU")
        private String menu; // 메뉴 목록

        @JsonProperty("TELNO")
        private String telno; // 가게 전화번호

        @JsonProperty("_URL")
        private String url; // 가게 홈페이지 URL

        @JsonProperty("ADRES")
        private String adres; // 가게 주소

        @JsonProperty("LA")
        private double la; // 위도

        @JsonProperty("TIME")
        private String time; // 운영시간

        @JsonProperty("DC")
        private String dc; // 가게 상세 설명
    }

    //Header를 정의
    @Data
    public static class Header{
        private int perPage;
        private String resultCode;
        private int totalRows;
        private int currentPage;
        private String resultMsg;
    }

}
