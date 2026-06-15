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
                "You are a friendly health assistant. " +
                        "Explain medical history analysis in very simple language " +
                        "that a common person can understand easily. " +
                        "Patient History: " +
                        "Blood Group: %s, " +
                        "Existing Conditions: %s, " +
                        "Current Medications: %s, " +
                        "Allergies: %s. " +
                        "Current Health Issue: %s. " +
                        "Please provide in this EXACT format:\n\n" +
                        "⚕️ HOW YOUR HISTORY AFFECTS YOU:\n" +
                        "(Explain in simple 2-3 sentences)\n\n" +
                        "💊 MEDICINE WARNINGS:\n" +
                        "• (Simple warning 1 if any)\n" +
                        "• (Simple warning 2 if any)\n\n" +
                        "🛡️ EXTRA PRECAUTIONS FOR YOU:\n" +
                        "• (Simple precaution 1)\n" +
                        "• (Simple precaution 2)\n\n" +
                        "Use very simple everyday language. No medical terms.",
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