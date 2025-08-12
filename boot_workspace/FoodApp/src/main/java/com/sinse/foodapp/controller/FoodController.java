package com.sinse.foodapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@RestController
public class FoodController {

    private String serviceKey="vzb47%2FTnsL5t93ciUjDyGl5PAsSYpRAc0%2B7lpZeq6YO78Ud3kR%2FEK3Tmvvoqn%2Fu7nY%2BPHIwJ4HmQnAhBArnDGw%3D%3D";
    private String url = "http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo";
    @GetMapping("/stores")
    public String getList() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(url); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("currentPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("CMPNM","UTF-8") + "=" + URLEncoder.encode("지선생쌈촌", "UTF-8")); /*상호명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return null;
    }




}
