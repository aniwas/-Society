package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class viewAnnouncementActivity extends AppCompatActivity {

    private LinearLayout announcementContainer;
    private AnnouncementHelper announcementHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcement);

        announcementHelper = new AnnouncementHelper(this);

        List<String> announcements = announcementHelper.getAllAnnouncements();

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        boolean isFirst = true;

        for (String announcement : announcements) {
            TableRow row = new TableRow(this);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(announcement);
            nameTextView.setPadding(8, 8, 8, 8);
            nameTextView.setTextColor(Color.WHITE);
            row.addView(nameTextView);
            if(isFirst)row.setBackgroundColor(Color.parseColor("#A10B3E"));
            else row.setBackgroundColor(Color.parseColor("#610524"));

            isFirst = !isFirst;

            tableLayout.addView(row);

        }
    }
}
