package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText flatNo,password;
    Button loginbtn,redirectRegistration,securityloginbtn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flatNo = (EditText) findViewById(R.id.loginFlatNo);
        password = (EditText) findViewById(R.id.loginpassword);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        redirectRegistration = (Button) findViewById(R.id.toregistration);
        securityloginbtn = (Button) findViewById(R.id.securityloginbtn);
        DB = new DBHelper(this);

        AmenityHelper temp = new AmenityHelper(this);
        temp.createAmenities();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flat = flatNo.getText().toString();
                String pass = password.getText().toString();

                if(flat.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all fields!",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkFlatPass = DB.checkFlatPassword(flat,pass);
                    if(checkFlatPass){
                        Toast.makeText(MainActivity.this,"Signed in Successfully!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("Flat",flat);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        redirectRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        securityloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SecurityLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}