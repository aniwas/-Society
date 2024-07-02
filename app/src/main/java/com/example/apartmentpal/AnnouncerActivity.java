package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnnouncerActivity extends AppCompatActivity {
    AnnouncementHelper announceHelper;

    VisitorDBHelper visitor;
    Button announceButton,clearButton;
    EditText announcement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcer);

        visitor = new VisitorDBHelper(this);
        announceHelper = new AnnouncementHelper(this);
        announceButton = (Button) findViewById(R.id.announceButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        announcement = (EditText) findViewById(R.id.announcmentEditText);

        announceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = announcement.getText().toString();
                boolean check = announceHelper.addAnnouncement(message);
                if(check) Toast.makeText(getApplicationContext(),"Announcement Made!",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"Announcement Failed!",Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //boolean check = visitor.clearVisitorLog();
                boolean check = announceHelper.clearAnnouncements();
                if(check) Toast.makeText(getApplicationContext(),"Announcements Cleared!",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}