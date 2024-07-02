package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apartmentpal.ChatContract;
import com.example.apartmentpal.ChatDBHelper;


public class SecurityActivity extends AppCompatActivity {
    Button sendVisitorRequest,viewAllowedButton,viewComplaints,amenityButton,clearChatButton,announceButton;
    private ChatDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        sendVisitorRequest = (Button) findViewById(R.id.visitorRequestButton);
        viewAllowedButton = (Button) findViewById(R.id.viewAllowedButton);
        viewComplaints = (Button) findViewById(R.id.viewComplaintsBtn);
        amenityButton = (Button) findViewById(R.id.amenityButton);
        clearChatButton = (Button) findViewById(R.id.clearChatButton);
        announceButton = (Button) findViewById(R.id.announceButton);
        dbHelper = new ChatDBHelper(this);

        sendVisitorRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getApplicationContext(), sendVisitorRequest.class);
                    startActivity(intent);
                }
                catch (Exception e){}
            }
        });

        viewAllowedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AllAllowed.class);
                startActivity(intent);
            }

        });

        viewComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),viewComplaints.class);
                startActivity(intent);
            }
        });

        amenityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AmenityManager.class);
                startActivity(intent);
            }
        });

        clearChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete(ChatContract.ChatEntry.TABLE_NAME, null, null);
                database.close();
                //chatMessagesLayout.removeAllViews(); // Clear messages displayed in the layout
                Toast.makeText(getApplicationContext(), "Chat Cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        announceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AnnouncerActivity.class);
                startActivity(intent);
            }
        });
    }
}