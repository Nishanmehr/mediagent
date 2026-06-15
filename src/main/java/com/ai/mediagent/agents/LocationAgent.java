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

        List<NearbyDoctor> doctors = new ArrayList<>();

        if (location.toLowerCase().contains("noida")) {
            doctors.add(createDoctor(
                    "Dr. Rajesh Sharma", specialization,
                    4.9, "₹800", "2.3 km",
                    "Available Today", "Apollo Hospital",
                    "Sector 26, Noida"));
            doctors.add(createDoctor(
                    "Dr. Priya Gupta", specialization,
                    4.7, "₹600", "4.1 km",
                    "Tomorrow", "Fortis Hospital",
                    "Sector 62, Noida"));
            doctors.add(createDoctor(
                    "Dr. Amit Verma", specialization,
                    4.8, "₹1200", "5.0 km",
                    "Available Today", "Max Hospital",
                    "Sector 19, Noida"));

        } else if (location.toLowerCase().contains("delhi")) {
            doctors.add(createDoctor(
                    "Dr. Sunita Sharma", specialization,
                    4.9, "₹1000", "1.5 km",
                    "Available Today", "AIIMS Delhi",
                    "Ansari Nagar, Delhi"));
            doctors.add(createDoctor(
                    "Dr. Rahul Khanna", specialization,
                    4.8, "₹900", "3.2 km",
                    "Available Today", "Sir Ganga Ram Hospital",
                    "Rajinder Nagar, Delhi"));
            doctors.add(createDoctor(
                    "Dr. Meena Patel", specialization,
                    4.6, "₹750", "4.5 km",
                    "Tomorrow", "Safdarjung Hospital",
                    "Ansari Nagar West, Delhi"));

        } else if (location.toLowerCase().contains("gurgaon") ||
                location.toLowerCase().contains("gurugram")) {
            doctors.add(createDoctor(
                    "Dr. Vikram Singh", specialization,
                    4.9, "₹1500", "2.1 km",
                    "Available Today", "Medanta Hospital",
                    "Sector 38, Gurgaon"));
            doctors.add(createDoctor(
                    "Dr. Anjali Mehta", specialization,
                    4.7, "₹1200", "3.8 km",
                    "Tomorrow", "Artemis Hospital",
                    "Sector 51, Gurgaon"));
            doctors.add(createDoctor(
                    "Dr. Ravi Kumar", specialization,
                    4.8, "₹1000", "5.2 km",
                    "Available Today", "Columbia Asia Hospital",
                    "Sector 47, Gurgaon"));

        } else if (location.toLowerCase().contains("mumbai")) {
            doctors.add(createDoctor(
                    "Dr. Deepak Shah", specialization,
                    4.9, "₹1500", "1.8 km",
                    "Available Today", "Lilavati Hospital",
                    "Bandra West, Mumbai"));
            doctors.add(createDoctor(
                    "Dr. Pooja Desai", specialization,
                    4.8, "₹1200", "3.5 km",
                    "Tomorrow", "Kokilaben Hospital",
                    "Andheri West, Mumbai"));
            doctors.add(createDoctor(
                    "Dr. Suresh Nair", specialization,
                    4.7, "₹1000", "4.2 km",
                    "Available Today", "Breach Candy Hospital",
                    "Breach Candy, Mumbai"));

        } else if (location.toLowerCase().contains("bangalore") ||
                location.toLowerCase().contains("bengaluru")) {
            doctors.add(createDoctor(
                    "Dr. Kiran Rao", specialization,
                    4.9, "₹1200", "2.0 km",
                    "Available Today", "Manipal Hospital",
                    "HAL Airport Road, Bangalore"));
            doctors.add(createDoctor(
                    "Dr. Lakshmi Reddy", specialization,
                    4.8, "₹1000", "3.5 km",
                    "Tomorrow", "Fortis Hospital",
                    "Bannerghatta Road, Bangalore"));
            doctors.add(createDoctor(
                    "Dr. Arjun Sharma", specialization,
                    4.7, "₹900", "5.0 km",
                    "Available Today", "Apollo Hospital",
                    "Bannerghatta Road, Bangalore"));

        } else {
            // Default — Any other city
            doctors.add(createDoctor(
                    "Dr. " + location + " Specialist 1",
                    specialization,
                    4.8, "₹800", "2.0 km",
                    "Available Today", "City Hospital",
                    "Main Road, " + location));
            doctors.add(createDoctor(
                    "Dr. " + location + " Specialist 2",
                    specialization,
                    4.6, "₹600", "3.5 km",
                    "Tomorrow", "General Hospital",
                    "Station Road, " + location));
            doctors.add(createDoctor(
                    "Dr. " + location + " Specialist 3",
                    specialization,
                    4.7, "₹1000", "5.0 km",
                    "Available Today", "Medical Center",
                    "Park Street, " + location));
        }

        System.out.println("✅ Agent 4: " +
                doctors.size() + " doctors found!");
        return doctors;
    }

    private NearbyDoctor createDoctor(
            String name, String spec,
            double rating, String fees,
            String distance, String availability,
            String hospital, String address) {
        NearbyDoctor doc = new NearbyDoctor();
        doc.setName(name);
        doc.setSpecialization(spec);
        doc.setRating(rating);
        doc.setFees(fees);
        doc.setDistance(distance);
        doc.setAvailability(availability);
        doc.setHospital(hospital);
        doc.setAddress(address);
        return doc;
    }
}