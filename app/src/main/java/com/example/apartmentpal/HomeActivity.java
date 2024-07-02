package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView welcome;
    Button btnVisitors,btnPayFee,btnComplain,btnAmenity,btnChat,emergencyButton,announcementButton;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = (TextView) findViewById(R.id.welcomeText);
        btnVisitors = (Button) findViewById(R.id.btnVisitors);
        btnPayFee = (Button) findViewById(R.id.btnPayFee);
        btnComplain = (Button) findViewById(R.id.btnComplain);
        btnAmenity = (Button) findViewById(R.id.amenityBtn);
        btnChat = (Button) findViewById(R.id.chatButton);
        emergencyButton = (Button) findViewById(R.id.emergencyButton);
        announcementButton = (Button) findViewById(R.id.announcementBtn);

        db = new DBHelper(this);

        Intent intent = getIntent();
        String flatNo = intent.getStringExtra("Flat");
        String user = db.getUser(flatNo);
        welcome.setText("Welcome, "+user);

        btnVisitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),VisitorsActivity.class);
                intent.putExtra("Username",user);
                intent.putExtra("flatNo",flatNo);
                startActivity(intent);
            }
        });

        btnPayFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PayAmount.class);
                startActivity(intent);
            }
        });

        btnComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SendComplaint.class);
                intent.putExtra("name",user);
                intent.putExtra("flat",flatNo);

                startActivity(intent);
            }
        });

        btnAmenity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AmenityActivity.class);
                startActivity(intent);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatroomActivity.class);
                intent.putExtra("name",user);
                startActivity(intent);
            }
        });

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7977756352"));
                startActivity(intent);
            }
        });

        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),viewAnnouncementActivity.class);
                startActivity(intent);
            }
        });
    }
}