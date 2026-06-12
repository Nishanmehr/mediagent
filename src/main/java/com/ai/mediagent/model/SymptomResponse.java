package com.ai.mediagent.model;

import lombok.Data;
import java.util.List;

@Data
public class SymptomResponse {
    private String analysis;
    private String possibleConditions;
    private String severity;
    private String recommendedSpecialist;
    private List<String> nextSteps;
}