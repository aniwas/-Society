package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class viewComplaints extends AppCompatActivity {
    ComplaintDBHelper db;
    boolean alternateColor = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);

        db = new ComplaintDBHelper(this);

        List<List<String>> receivedComplaints = db.getReceivedComplaints();

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (List<String> complaint : receivedComplaints) {
            TableRow row = new TableRow(this);

            // Set background color based on alternateColor boolean
            int bgColor = alternateColor ? Color.parseColor("#A10B3E") : Color.parseColor("#610524");
            row.setBackgroundColor(bgColor);
            alternateColor = !alternateColor; // Toggle color for the next row

            TextView idTextView = new TextView(this);
            idTextView.setText(complaint.get(0));
            idTextView.setPadding(8, 8, 8, 8);
            idTextView.setTextColor(Color.WHITE);
            row.addView(idTextView);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(complaint.get(1));
            nameTextView.setPadding(8, 8, 8, 8);
            nameTextView.setTextColor(Color.WHITE);
            row.addView(nameTextView);

            TextView complaintTextView = new TextView(this);
            complaintTextView.setText(complaint.get(3));
            complaintTextView.setPadding(8, 8, 8, 8);
            complaintTextView.setTextColor(Color.WHITE);
            row.addView(complaintTextView);

            TextView timeTextView = new TextView(this);
            timeTextView.setText(complaint.get(4));
            timeTextView.setPadding(8, 8, 8, 8);
            timeTextView.setTextColor(Color.WHITE);
            row.addView(timeTextView);

            Button resolveButton = new Button(this);
            resolveButton.setText("Resolve");
            resolveButton.setOnClickListener(v -> {
                boolean resolve = db.resolve(complaint.get(0));
                if (resolve)
                    Toast.makeText(this, "Complaint Resolved!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Error! Unable To Resolve!", Toast.LENGTH_SHORT).show();
            });
            row.addView(resolveButton);

            tableLayout.addView(row);
        }
    }
}
