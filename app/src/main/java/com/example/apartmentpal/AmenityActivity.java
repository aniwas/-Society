package com.example.apartmentpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class AmenityActivity extends AppCompatActivity {
    AmenityHelper amenityHelper;
    Button gymButton,swimmingPoolButton,groundButton,partyHallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenity);

        gymButton = findViewById(R.id.buttonGym);
        swimmingPoolButton = findViewById(R.id.buttonSwimmingPool);
        groundButton = findViewById(R.id.buttonGround);
        partyHallButton = findViewById(R.id.buttonPartyHall);
        amenityHelper = new AmenityHelper(this);


        // ... Other initialization code

        // Assuming you have a button in your layout with ID: buttonGym
        gymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSlotSelectionWindow(amenityHelper.getAvailableTimeSlots("Gym"),"Gym"); // Assuming Gym is the first amenity
            }
        });

        swimmingPoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSlotSelectionWindow(amenityHelper.getAvailableTimeSlots("Swimming Pool"),"Swimming Pool"); // Assuming Gym is the first amenity
            }
        });

        groundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSlotSelectionWindow(amenityHelper.getAvailableTimeSlots("Ground"),"Ground"); // Assuming Gym is the first amenity
            }
        });

        partyHallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSlotSelectionWindow(amenityHelper.getAvailableTimeSlots("Party Hall"),"Party Hall"); // Assuming Gym is the first amenity
            }
        });

        // Similarly, set OnClickListener for other buttons (Swimming Pool, Ground, Party Hall) if they exist
    }

    private void showTimeSlotSelectionWindow(List<String> availableSlots,String aminety) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.time_slot_selection, null);
        dialogBuilder.setView(dialogView);

        final RadioGroup radioGroupTimeSlots = dialogView.findViewById(R.id.radioGroupTimeSlots);
        for (String slot : availableSlots) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(slot);
            radioGroupTimeSlots.addView(radioButton);
        }

        Button buttonBook = dialogView.findViewById(R.id.buttonBook);
        final AlertDialog alertDialog = dialogBuilder.create();
        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = radioGroupTimeSlots.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = radioGroupTimeSlots.findViewById(selectedRadioButtonId);
                    String selectedTimeSlot = selectedRadioButton.getText().toString();
                    // Perform booking operation using amenity.bookTimeSlot(selectedTimeSlot);
                    // You can handle the booking logic here
                    boolean temp;
                    if(selectedTimeSlot.equals("9AM TO 12PM")) temp = amenityHelper.bookTimeSlot("slot9AMto12PM",aminety);
                    else if(selectedTimeSlot.equals("12PM TO 3PM")) amenityHelper.bookTimeSlot("slot12PMto3PM",aminety);
                    else if(selectedTimeSlot.equals("3PM TO 6PM")) amenityHelper.bookTimeSlot("slot3PMto6PM",aminety);
                    alertDialog.dismiss(); // Close the dialog after booking
                } else {
                    // Handle case when no radio button is selected
                    Toast.makeText(AmenityActivity.this, "Please select a time slot", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }
}