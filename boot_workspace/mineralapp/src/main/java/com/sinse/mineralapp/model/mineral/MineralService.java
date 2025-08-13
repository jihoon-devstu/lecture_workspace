package com.sinse.mineralapp.model.mineral;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class MineralService {
    private String serviceKey = "vzb47%2FTnsL5t93ciUjDyGl5PAsSYpRAc0%2B7lpZeq6YO78Ud3kR%2FEK3Tmvvoqn%2Fu7nY%2BPHIwJ4HmQnAhBArnDGw%3D%3D";
    private String baseUrl = "http://apis.data.go.kr/6460000/jnYaksoo/getYakSooList";


    private MineralHandler mineralHandler;
    public MineralService(MineralHandler mineralHandler) {
        this.mineralHandler = mineralHandler;
    }

    public List<Mineral> parse() throws IOException, InterruptedException, ParserConfigurationException, SAXException {

        String url = baseUrl + "?" +
                "serviceKey="+serviceKey+
                "&title="+URLEncoder.encode("꽃무릇", StandardCharsets.UTF_8)+
                "&pageSize="+ URLEncoder.encode("10", StandardCharsets.UTF_8) +
                "&startPage="+ URLEncoder.encode("1", StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header( "Accept", "application/xml")
                .GET()
                .build();
        HttpResponse<String> response=client.send(request , HttpResponse.BodyHandlers.ofString());


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        InputSource inputSource = new InputSource(new StringReader(response.body()));

        saxParser.parse(inputSource, mineralHandler);


        System.out.println("OpenAPI XML response: " + response.body());

        return mineralHandler.getMineralList();
    }


}
