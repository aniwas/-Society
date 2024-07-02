package com.example.apartmentpal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Amenity {
    String amenityName;
    Map<String, Boolean> timeSlots;

    // Constructor
    public Amenity(String name) {
        amenityName = name;
        timeSlots = new HashMap<>(); // Initialize timeSlots
        timeSlots.put("9AM - 12PM", false);
        timeSlots.put("12PM - 3PM", false);
        timeSlots.put("3PM - 6PM", false);
        timeSlots.put("6PM - 9PM", false);
    }

    // Method to book a time slot
    public boolean bookTimeSlot(String timeSlot) {
        if(!timeSlots.get(timeSlot)) {
            timeSlots.put(timeSlot, true);
            return true;
        } else {
            return false;
        }
    }

    // Method to cancel a time slot
    public boolean cancelTimeSlot(String timeSlot) {
        if(timeSlots.get(timeSlot)) {
            timeSlots.put(timeSlot, false);
            return true;
        } else {
            return false;
        }
    }

    // Method to get booked time slots
    public List<String> getBookedTimeSlots() {
        List<String> bookedTimeSlots = new ArrayList<>();

        for(Map.Entry<String, Boolean> entry : timeSlots.entrySet()){
            if(entry.getValue()) {
                bookedTimeSlots.add(entry.getKey());
            }
        }
        return bookedTimeSlots;
    }

    // Method to get available time slots
    public List<String> getAvailableTimeSlots() {
        List<String> availableTimeSlots = new ArrayList<>();

        for(Map.Entry<String, Boolean> entry : timeSlots.entrySet()){
            if(!entry.getValue()) {
                availableTimeSlots.add(entry.getKey());
            }
        }
        return availableTimeSlots;
    }
}
