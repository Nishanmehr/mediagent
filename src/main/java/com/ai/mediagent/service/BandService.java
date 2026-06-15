package com.ai.mediagent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

@Service
public class BandService {

    @Value("${band.api.key}")
    private String bandApiKey;

    @Value("${band.room.id}")
    private String roomId;

    private final WebClient webClient;

    public BandService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.band.ai")
                .build();
    }

    public void postMessage(String agentName,
                            String message) {
        try {
            System.out.println("📡 Band: " +
                    agentName + " posting...");

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("content",
                    "🤖 " + agentName + ":\n" + message);
            requestBody.put("room_id", roomId);

            webClient.post()
                    .uri("/v1/messages")
                    .header("Authorization",
                            "Bearer " + bandApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            System.out.println("✅ Band: Message posted!");

        } catch (Exception e) {
            System.out.println("⚠️ Band Error: "
                    + e.getMessage());
        }
    }
}