package com.example.chat.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.openai.com/v1/chat/completions";
//    private final String apiKey = "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";

    public ChatServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

@Override
public String sendMessage(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth("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");
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> requestPayload = new HashMap<>();
    Map<String, String> messageData = new HashMap<>();
    messageData.put("role", "user");
    messageData.put("content", message);
    List<Map<String, String>> messages = new ArrayList<>();
    messages.add(messageData);
    requestPayload.put("messages", messages);
    requestPayload.put("model", "gpt-3.5-turbo");

    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestPayload, headers);

    ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
    return responseEntity.getBody();
}


}
