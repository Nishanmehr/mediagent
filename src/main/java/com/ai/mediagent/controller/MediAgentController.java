package com.ai.mediagent.controller;

import com.ai.mediagent.agents.DoctorMatcherAgent;
import com.ai.mediagent.agents.LocationAgent;
import com.ai.mediagent.agents.MedicalHistoryAgent;
import com.ai.mediagent.agents.SymptomAnalyzerAgent;
import com.ai.mediagent.agents.CarePlannerAgent;
import com.ai.mediagent.model.MedicalHistory;
import com.ai.mediagent.model.NearbyDoctor;
import com.ai.mediagent.model.SymptomRequest;
import com.ai.mediagent.model.SymptomResponse;
import com.ai.mediagent.service.BandService;
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
    private final CarePlannerAgent carePlannerAgent;
    private final BandService bandService;


    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeSymptoms(
            @RequestBody SymptomRequest request) {

        System.out.println("📥 Request received...");

        // Agent 1
        SymptomResponse symptomResponse =
                symptomAnalyzerAgent.analyze(request);
        bandService.postMessage(
                "Symptom Analyzer Agent",
                "Patient symptoms analyzed. Severity: "
                        + symptomResponse.getSeverity()
                        + " | Specialist needed: "
                        + symptomResponse.getRecommendedSpecialist()
        );

        // Agent 2
        MedicalHistory history = new MedicalHistory();
        history.setBloodGroup(request.getBloodGroup());
        history.setExistingConditions(
                request.getExistingConditions());
        history.setCurrentMedications(
                request.getCurrentMedications());
        history.setAllergies(request.getAllergies());
        String historyAnalysis = medicalHistoryAgent
                .analyze(history,
                        symptomResponse);
        bandService.postMessage(
                "Medical History Agent",
                "Medical history analyzed. " +
                        "Risk factors and medication conflicts checked."
        );

        // Agent 3
        String doctorMatch = doctorMatcherAgent
                .matchDoctor(symptomResponse,
                        historyAnalysis);
        bandService.postMessage(
                "Doctor Matcher Agent",
                "Best specialist identified: "
                        + symptomResponse.getRecommendedSpecialist()
                        + " | Urgency: Emergency"
        );

        // Agent 4
        List<NearbyDoctor> nearbyDoctors = locationAgent
                .findNearbyDoctors(
                        request.getLocation(),
                        symptomResponse
                                .getRecommendedSpecialist()
                );
        bandService.postMessage(
                "Location Agent",
                nearbyDoctors.size()
                        + " nearby doctors found in "
                        + request.getLocation()
                        + " | Top: "
                        + nearbyDoctors.get(0).getName()
                        + " ("
                        + nearbyDoctors.get(0).getHospital()
                        + ")"
        );

        // Agent 5
        String carePlan = carePlannerAgent.createCarePlan(
                symptomResponse,
                historyAnalysis,
                doctorMatch,
                request.getLocation()
        );
        bandService.postMessage(
                "Care Planner Agent",
                "Complete care plan created for patient in "
                        + request.getLocation()
                        + ". Immediate actions and follow-up schedule ready."
        );

        // Final Response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("symptomAnalysis", symptomResponse);
        response.put("historyAnalysis", historyAnalysis);
        response.put("doctorMatch", doctorMatch);
        response.put("nearbyDoctors", nearbyDoctors);
        response.put("carePlan", carePlan);

        return ResponseEntity.ok(response);
    }
}