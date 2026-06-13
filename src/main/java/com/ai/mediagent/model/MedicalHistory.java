package com.ai.mediagent.model;

import lombok.Data;
import java.util.List;

@Data
public class MedicalHistory {
    private String patientName;
    private String bloodGroup;
    private List<String> existingConditions;
    private List<String> currentMedications;
    private List<String> allergies;
    private String previousSurgeries;
}