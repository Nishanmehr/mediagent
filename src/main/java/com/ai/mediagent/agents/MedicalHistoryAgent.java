package com.ai.mediagent.agents;

import com.ai.mediagent.model.MedicalHistory;
import com.ai.mediagent.model.SymptomResponse;
import com.ai.mediagent.service.AIMLApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalHistoryAgent {

    private final AIMLApiService aimlApiService;

    public String analyze(MedicalHistory history,
                                 SymptomResponse symptomResponse) {

        System.out.println("📋 Agent 2: Checking medical history...");

        String prompt = String.format(
                "You are a medical history analyzer agent. " +
                        "Patient History: " +
                        "Blood Group: %s, " +
                        "Existing Conditions: %s, " +
                        "Current Medications: %s, " +
                        "Allergies: %s. " +
                        "Current Symptom Analysis: %s. " +
                        "Based on this history: " +
                        "1. Are there any risk factors? " +
                        "2. Any medication conflicts? " +
                        "3. Does history worsen current symptoms? " +
                        "4. Special precautions needed? " +
                        "Be concise and professional.",
                history.getBloodGroup(),
                history.getExistingConditions(),
                history.getCurrentMedications(),
                history.getAllergies(),
                symptomResponse.getAnalysis()
        );

        String result = aimlApiService
                .analyzeSymptoms(prompt, "", "");

        System.out.println("✅ Agent 2: History analysis complete!");
        return result;
    }
}