package com.ai.mediagent.agents;

import com.ai.mediagent.model.SymptomResponse;
import com.ai.mediagent.service.AIMLApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarePlannerAgent {

    private final AIMLApiService aimlApiService;

    public String createCarePlan(
            SymptomResponse symptomResponse,
            String historyAnalysis,
            String doctorMatch,
            String location) {

        System.out.println("🏥 Agent 5: Creating care plan...");

        String prompt = String.format(
                "You are a caring health guide helping a common person. " +
                        "Create a simple, easy-to-follow care plan. " +
                        "Symptom Analysis: %s. " +
                        "Medical History: %s. " +
                        "Doctor Recommendation: %s. " +
                        "Location: %s. " +
                        "Please provide in this EXACT format:\n\n" +
                        "🚨 DO THIS RIGHT NOW (Today):\n" +
                        "• (Simple action 1)\n" +
                        "• (Simple action 2)\n\n" +
                        "📅 THIS WEEK:\n" +
                        "• (Simple action 1)\n" +
                        "• (Simple action 2)\n\n" +
                        "🍎 WHAT TO EAT & AVOID:\n" +
                        "✅ Eat: (simple foods list)\n" +
                        "❌ Avoid: (simple foods list)\n\n" +
                        "💊 MEDICINES:\n" +
                        "• (Simple medicine advice)\n\n" +
                        "🚫 WARNING SIGNS — GO TO HOSPITAL IF:\n" +
                        "• (Simple warning sign 1)\n" +
                        "• (Simple warning sign 2)\n" +
                        "• (Simple warning sign 3)\n\n" +
                        "Use very simple Hindi-friendly English. " +
                        "Write like you are talking to a friend.",
                symptomResponse.getAnalysis(),
                historyAnalysis,
                doctorMatch,
                location
        );

        String result = aimlApiService.analyzeWithPrompt(prompt);
        System.out.println("✅ Agent 5: Care plan created!");
        return result;
    }
}