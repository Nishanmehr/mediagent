package com.ai.mediagent.agents;

import com.ai.mediagent.model.SymptomRequest;
import com.ai.mediagent.model.SymptomResponse;
import com.ai.mediagent.service.AIMLApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymptomAnalyzerAgent {

    private final AIMLApiService aimlApiService;

    public SymptomResponse analyze(SymptomRequest request) {
        System.out.println("🔍 Agent 1: Analyzing symptoms...");

        String analysis = aimlApiService.analyzeSymptoms(
                request.getSymptoms(),
                request.getAge(),
                request.getGender()
        );

        SymptomResponse response = new SymptomResponse();
        response.setAnalysis(analysis);
        response.setSeverity(extractSeverity(analysis));
        response.setRecommendedSpecialist(
                extractSpecialist(analysis)
        );

        System.out.println("✅ Agent 1: Analysis complete!");
        return response;
    }

    private String extractSeverity(String analysis) {
        if (analysis.toLowerCase().contains("high"))
            return "HIGH";
        if (analysis.toLowerCase().contains("medium"))
            return "MEDIUM";
        return "LOW";
    }

    private String extractSpecialist(String analysis) {
        if (analysis.toLowerCase().contains("cardiologist"))
            return "Cardiologist";
        if (analysis.toLowerCase().contains("neurologist"))
            return "Neurologist";
        if (analysis.toLowerCase().contains("orthopedic"))
            return "Orthopedic";
        return "General Physician";
    }
}