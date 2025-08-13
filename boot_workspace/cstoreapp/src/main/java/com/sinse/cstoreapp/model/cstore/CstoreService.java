package com.sinse.cstoreapp.model.cstore;

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
public class CstoreService {
    private String serviceKey = "24447f5f9258417292a96850c144b7f3";
    private String baseUrl = "https://openapi.gg.go.kr/Resrestrtcvnstr";

    public List<Cstore> parse() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        String url = baseUrl + "?" +
                "KEY=" + serviceKey +
                "&Type=xml" +
                "&pIndex=1" +
                "&pSize=10";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header( "Content-Type", "application/xml")
                .GET()
                .build();
        HttpResponse<String> response=client.send(request , HttpResponse.BodyHandlers.ofString());
        CstoreHandler cstoreHandler = new CstoreHandler();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        InputSource inputSource = new InputSource(new StringReader(response.body()));

        saxParser.parse(inputSource, cstoreHandler);
        System.out.println("cstores parsed: " +cstoreHandler.getCstoreList().size());

        return cstoreHandler.getCstoreList();
    }

}
