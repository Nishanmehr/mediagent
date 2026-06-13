package com.ai.mediagent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

@Service
public class AIMLApiService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public AIMLApiService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public String analyzeSymptoms(String symptoms,
                                  String age,
                                  String gender) {
        String prompt = String.format(
                "You are a medical symptom analyzer agent. " +
                        "Patient Details: Age: %s, Gender: %s. " +
                        "Symptoms: %s. " +
                        "Analyze and provide: " +
                        "1. Possible conditions " +
                        "2. Severity level (Low/Medium/High) " +
                        "3. Recommended specialist type " +
                        "4. Immediate next steps.",
                age, gender, symptoms
        );
        return callGroqAPI(prompt);
    }

    public String analyzeWithPrompt(String prompt) {
        return callGroqAPI(prompt);
    }

    private String callGroqAPI(String prompt) {
        try {
            String jsonBody = "{"
                    + "\"model\": \"llama-3.3-70b-versatile\","
                    + "\"messages\": ["
                    + "  {"
                    + "    \"role\": \"user\","
                    + "    \"content\": \""
                    + prompt.replace("\"", "'")
                    .replace("\n", " ")
                    .replace("\r", " ")
                    + "\""
                    + "  }"
                    + "],"
                    + "\"max_tokens\": 500,"
                    + "\"temperature\": 0.7"
                    + "}";

            // DEBUG — print karo
            System.out.println("=== GROQ REQUEST ===");
            System.out.println(jsonBody);
            System.out.println("=== API KEY ===");
            System.out.println(apiKey.substring(0, 10) + "...");

            return webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(jsonBody)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> response.bodyToMono(String.class)
                                    .map(body -> {
                                        // ERROR BODY PRINT KARO
                                        System.out.println("=== ERROR BODY ===");
                                        System.out.println(body);
                                        return new RuntimeException(body);
                                    }))
                    .bodyToMono(Map.class)
                    .map(response -> {
                        List<Map> choices =
                                (List<Map>) response.get("choices");
                        Map msg =
                                (Map) choices.get(0).get("message");
                        return (String) msg.get("content");
                    })
                    .block();

        } catch (Exception e) {
            System.out.println("=== EXCEPTION ===");
            System.out.println(e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}