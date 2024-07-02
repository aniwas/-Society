package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendComplaint extends AppCompatActivity {
    EditText complaintBox;
    Button sendButton;
    ComplaintDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaint);

        complaintBox = (EditText) findViewById(R.id.complaintEditText);
        sendButton = (Button) findViewById(R.id.fileComplaintButton);
        db = new ComplaintDBHelper(this);

        Intent intent = getIntent();
        String user = intent.getStringExtra("name");
        String flat = intent.getStringExtra("flat");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaint = complaintBox.getText().toString();
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time = currentDateTime.format(formatter);
                boolean sent = db.addComplaint(user,flat,complaint,time);
                if(sent){
                    Toast.makeText(getApplicationContext(),"Complaint Raised!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error! Unable to Raise Complaint!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}