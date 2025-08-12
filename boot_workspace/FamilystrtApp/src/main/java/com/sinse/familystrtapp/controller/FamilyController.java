package com.sinse.familystrtapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.familystrtapp.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class FamilyController {

    private String serviceKey="051aeeddf1db46e783da0ffd00d39f30";
    private String baseUrl = "https://openapi.gg.go.kr/Familyrstrt";

    @GetMapping("/stores")
    public ApiResponse getStores() throws IOException, InterruptedException {
        String url = baseUrl + "?" +
                "Key=" + serviceKey +
                "&Type=json" +
                "&plndex=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&pSize=" + URLEncoder.encode("100", StandardCharsets.UTF_8);

        //HttpClient 로 연결 요청 (HttpUrlConnection 보다 개선되어있는 객체)
        HttpClient client = HttpClient.newHttpClient();

        //요청정보 객체
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header( "Content-Type", "application/json")
                .GET()
                .build();

        //Open API 서버에 요청
        HttpResponse<String> response=client.send(request , HttpResponse.BodyHandlers.ofString());

        //String 자체를 클라이언트에게 전송해버리면 , 클라이언트가 문자열을 json으로 파싱해야 하고,
        //이 방법은 권장되지 않는다.
        //따라서 지금부터 Open API 의 String 결과물을 자바의 클래스에 매핑시키되 , Jackson을 이용하여 자동화 시키겠다.
        //클라이언트에게 응답 정보로 사용할 수 있을 뿐 아니라 , 객체화 되어 있기 때문에 서버에서도
        //활용할 수 있다..

        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse =objectMapper.readValue(response.body(), ApiResponse.class);
        return apiResponse;

    }
}
