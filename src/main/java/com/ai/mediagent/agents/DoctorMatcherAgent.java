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
                "You are a doctor matching agent. " +
                        "Symptom Analysis: %s. " +
                        "Medical History Analysis: %s. " +
                        "Recommended Specialist: %s. " +
                        "Based on this information provide: " +
                        "1. Best specialist type needed " +
                        "2. Urgency level (Emergency/Urgent/Normal) " +
                        "3. Why this specialist is needed " +
                        "4. What to tell the doctor " +
                        "Be concise and professional.",
                symptomResponse.getAnalysis(),
                historyAnalysis,
                symptomResponse.getRecommendedSpecialist()
        );

        String result = aimlApiService.analyzeWithPrompt(prompt);
        System.out.println("✅ Agent 3: Doctor matched!");
        return result;
    }
}