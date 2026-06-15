package com.ai.mediagent.agents;

import com.ai.mediagent.model.SymptomResponse;
import com.ai.mediagent.service.AIMLApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorMatcherAgent {

    private final AIMLApiService aimlApiService;

    public String matchDoctor(SymptomResponse symptomResponse,
                              String historyAnalysis) {

        System.out.println("👨‍⚕️ Agent 3: Matching doctors...");

        String prompt = String.format(
                "You are a friendly health guide. " +
                        "Help a common person understand which doctor to visit. " +
                        "Use very simple language. " +
                        "Symptom Analysis: %s. " +
                        "Medical History: %s. " +
                        "Please provide in this EXACT format:\n\n" +
                        "🏥 WHICH DOCTOR YOU NEED:\n" +
                        "(Name the doctor type in simple words)\n\n" +
                        "⏰ HOW URGENTLY YOU NEED TO GO:\n" +
                        "(Emergency/Today/This Week — explain in one simple line)\n\n" +
                        "📋 WHAT TO TELL THE DOCTOR:\n" +
                        "• (Simple point 1)\n" +
                        "• (Simple point 2)\n" +
                        "• (Simple point 3)\n\n" +
                        "💡 SIMPLE TIP:\n" +
                        "(One helpful tip for the patient)\n\n" +
                        "Keep it super simple and friendly.",
                symptomResponse.getAnalysis(),
                historyAnalysis
        );

        String result = aimlApiService.analyzeWithPrompt(prompt);
        System.out.println("✅ Agent 3: Doctor matched!");
        return result;
    }
}