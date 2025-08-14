package com.sinse.busapp.model;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BusService {

    private String serviceKey = "vzb47%2FTnsL5t93ciUjDyGl5PAsSYpRAc0%2B7lpZeq6YO78Ud3kR%2FEK3Tmvvoqn%2Fu7nY%2BPHIwJ4HmQnAhBArnDGw%3D%3D";
    private String baseUrl = "http://apis.data.go.kr/6260000/BusanBIMS/busStopList";

    public List<Bus> parse() throws Exception{
        String url = baseUrl + "?" +
                "serviceKey=" + serviceKey +
                "&pageNo=1" +
                "&numOfRows=30";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header( "Content-Type", "application/xml")
                .GET()
                .build();
        HttpResponse<String> response=client.send(request , HttpResponse.BodyHandlers.ofString());
        BusHandler busHandler = new BusHandler();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        InputSource inputSource = new InputSource(new StringReader(response.body()));

        saxParser.parse(inputSource, busHandler);

        return busHandler.getBusList();
    }


}
