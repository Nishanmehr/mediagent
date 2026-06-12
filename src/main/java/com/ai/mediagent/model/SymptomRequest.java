package com.ai.mediagent.model;

import lombok.Data;

@Data
public class SymptomRequest {
    private String symptoms;
    private String age;
    private String gender;
    private String location;
}