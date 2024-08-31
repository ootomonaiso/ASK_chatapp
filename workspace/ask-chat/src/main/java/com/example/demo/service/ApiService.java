package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGreetingFromFlask(String name) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:5000/api/greet")
                .queryParam("name", name)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json; charset=UTF-8");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();
        if (responseBody != null) {
            try {
                // URLデコードする
                String decodedMessage = URLDecoder.decode(responseBody, StandardCharsets.UTF_8.name());
                return decodedMessage;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "デコードエラー";
            }
        }
        return "レスポンスが空です";
    }
}
