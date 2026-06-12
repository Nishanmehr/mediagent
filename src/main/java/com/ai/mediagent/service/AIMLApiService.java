package com.ai.mediagent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
import java.util.List;

@Service
public class AIMLApiService {

    @Value("${aiml.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public AIMLApiService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.aimlapi.com/v1")
                .build();
    }

    public String analyzeSymptoms(String symptoms,
                                  String age,
                                  String gender) {
        String prompt = String.format(
                "You are a medical symptom analyzer agent. " +
                        "Patient Details: Age: %s, Gender: %s. " +
                        "Symptoms: %s. " +
                        "Analyze these symptoms and provide: " +
                        "1. Possible conditions " +
                        "2. Severity level (Low/Medium/High) " +
                        "3. Recommended specialist type " +
                        "4. Immediate next steps. " +
                        "Be concise and professional.",
                age, gender, symptoms
        );

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 500
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map> choices = (List<Map>) response.get("choices");
                    Map message = (Map) choices.get(0).get("message");
                    return (String) message.get("content");
                })
                .block();
    }
}