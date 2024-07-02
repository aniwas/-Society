package com.example.apartmentpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VisitorsActivity extends AppCompatActivity {
    VisitorDBHelper visitorDB;
    DBHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Username");
        String flatNo = intent.getStringExtra("flatNo");

        visitorDB = new VisitorDBHelper(this);
        userDB = new DBHelper(this);

        List<Visitor> pendingVisitors = visitorDB.getPendingVisitors(flatNo);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        boolean isFirstRow = true;

        for (Visitor visitor : pendingVisitors) {
            TableRow row = new TableRow(this);

            int backgroundColor;
            if (isFirstRow) {
                backgroundColor = Color.parseColor("#A10B3E");
                isFirstRow = false;
            } else {
                backgroundColor = Color.parseColor("#610524");
                isFirstRow = true;
            }

            // Set background color for the row
            row.setBackgroundColor(backgroundColor);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(visitor.name);
            nameTextView.setTextColor(Color.WHITE);
            nameTextView.setPadding(8, 8, 8, 8);
            row.addView(nameTextView);

            TextView roleTextView = new TextView(this);
            roleTextView.setText(visitor.role);
            roleTextView.setTextColor(Color.WHITE);
            roleTextView.setPadding(8, 8, 8, 8);
            row.addView(roleTextView);

            Button allowButton = new Button(this);
            allowButton.setText("Allow");
            allowButton.setOnClickListener(v -> {
                VisitorDBHelper db = new VisitorDBHelper(this);
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                db.allow(visitor.id);
                db.insertIntoAllowed(visitor.name, visitor.role, flatNo, formattedDateTime);
                Toast.makeText(this, "Visitor Allowed Entry!", Toast.LENGTH_SHORT).show();
            });
            row.addView(allowButton);

            Button restrictButton = new Button(this);
            restrictButton.setText("Restrict");
            restrictButton.setOnClickListener(v -> {
                VisitorDBHelper db = new VisitorDBHelper(this);
                if (db.restrict(visitor.id))
                    Toast.makeText(this, "Visitor denied Entry!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Unable to handle request", Toast.LENGTH_SHORT).show();
            });
            row.addView(restrictButton);

            tableLayout.addView(row);
        }
    }
}