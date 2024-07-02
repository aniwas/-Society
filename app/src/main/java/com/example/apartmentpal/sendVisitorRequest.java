package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendVisitorRequest extends AppCompatActivity {
    EditText name,role,flat;
    Button sendBtn;
    DBHelper db;
    VisitorDBHelper visitorDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_visitor_request);

        name = (EditText) findViewById(R.id.nameEditText);
        role = (EditText) findViewById(R.id.roleEditText);
        flat = (EditText) findViewById(R.id.flatNoEditText);
        sendBtn = (Button) findViewById(R.id.sendRequstButton);
        db = new DBHelper(this);
        visitorDB = new VisitorDBHelper(this);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flatNo = flat.getText().toString();
                if(db.checkFlat(flatNo)){
                    visitorDB.insertVisitor(name.getText().toString(),role.getText().toString(),flat.getText().toString());
                    Toast.makeText(getApplicationContext(),"Request Sent to Owner",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error! Flat Does not Exist!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}