package com.ai.mediagent.controller;

import com.ai.mediagent.agents.DoctorMatcherAgent;
import com.ai.mediagent.agents.LocationAgent;
import com.ai.mediagent.agents.MedicalHistoryAgent;
import com.ai.mediagent.agents.SymptomAnalyzerAgent;
import com.ai.mediagent.model.MedicalHistory;
import com.ai.mediagent.model.NearbyDoctor;
import com.ai.mediagent.model.SymptomRequest;
import com.ai.mediagent.model.SymptomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mediagent")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MediAgentController {

    private final SymptomAnalyzerAgent symptomAnalyzerAgent;
    private final MedicalHistoryAgent medicalHistoryAgent;
    private final DoctorMatcherAgent doctorMatcherAgent;
    private final LocationAgent locationAgent;

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeSymptoms(
            @RequestBody SymptomRequest request) {

        System.out.println("📥 Request received...");

        // Agent 1 - Symptom Analysis
        SymptomResponse symptomResponse = symptomAnalyzerAgent
                .analyze(request);

        // Agent 2 - Medical History
        MedicalHistory history = new MedicalHistory();
        history.setBloodGroup(request.getBloodGroup());
        history.setExistingConditions(request.getExistingConditions());
        history.setCurrentMedications(request.getCurrentMedications());
        history.setAllergies(request.getAllergies());
        String historyAnalysis = medicalHistoryAgent
                .analyze(history, symptomResponse);

        // Agent 3 - Doctor Matcher
        String doctorMatch = doctorMatcherAgent
                .matchDoctor(symptomResponse,
                        historyAnalysis);

        // Agent 4 - Location Agent
        List<NearbyDoctor> nearbyDoctors = locationAgent
                .findNearbyDoctors(
                        request.getLocation(),
                        symptomResponse.getRecommendedSpecialist()
                );

        // Response build karo
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("symptomAnalysis", symptomResponse);
        response.put("historyAnalysis", historyAnalysis);
        response.put("doctorMatch", doctorMatch);
        response.put("nearbyDoctors", nearbyDoctors);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MediAgent is running! 🏥");
    }
}