package com.ai.mediagent.agents;

import com.ai.mediagent.model.NearbyDoctor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationAgent {

    public List<NearbyDoctor> findNearbyDoctors(
            String location,
            String specialization) {

        System.out.println("📍 Agent 4: Finding nearby doctors...");

        // Mock data — hackathon ke liye perfect
        List<NearbyDoctor> doctors = new ArrayList<>();

        NearbyDoctor doc1 = new NearbyDoctor();
        doc1.setName("Dr. Rajesh Sharma");
        doc1.setSpecialization(specialization);
        doc1.setRating(4.9);
        doc1.setFees("₹800");
        doc1.setDistance("2.3 km");
        doc1.setAvailability("Available Today");
        doc1.setHospital("Apollo Hospital");
        doc1.setAddress("Sector 26, " + location);

        NearbyDoctor doc2 = new NearbyDoctor();
        doc2.setName("Dr. Priya Gupta");
        doc2.setSpecialization(specialization);
        doc2.setRating(4.7);
        doc2.setFees("₹600");
        doc2.setDistance("4.1 km");
        doc2.setAvailability("Tomorrow");
        doc2.setHospital("Fortis Hospital");
        doc2.setAddress("Sector 62, " + location);

        NearbyDoctor doc3 = new NearbyDoctor();
        doc3.setName("Dr. Amit Verma");
        doc3.setSpecialization(specialization);
        doc3.setRating(4.8);
        doc3.setFees("₹1200");
        doc3.setDistance("5.0 km");
        doc3.setAvailability("Available Today");
        doc3.setHospital("Max Hospital");
        doc3.setAddress("Sector 19, " + location);

        doctors.add(doc1);
        doctors.add(doc2);
        doctors.add(doc3);

        System.out.println("✅ Agent 4: " +
                doctors.size() + " doctors found!");
        return doctors;
    }
}