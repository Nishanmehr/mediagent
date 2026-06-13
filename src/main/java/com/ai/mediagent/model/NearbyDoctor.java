package com.ai.mediagent.model;

import lombok.Data;

@Data
public class NearbyDoctor {
    private String name;
    private String specialization;
    private Double rating;
    private String fees;
    private String distance;
    private String availability;
    private String hospital;
    private String address;
}