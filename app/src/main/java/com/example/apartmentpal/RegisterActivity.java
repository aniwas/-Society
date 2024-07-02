package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,repassword,flatno;
    Button registerbtn;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerusername);
        password = (EditText) findViewById(R.id.registerpassword);
        repassword = (EditText) findViewById(R.id.reregisterpassword);
        flatno = (EditText) findViewById(R.id.flatno);
        registerbtn = (Button) findViewById(R.id.registerbtn);
        DB = new DBHelper(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String flatNo = flatno.getText().toString();

                if(flatNo.equals("") || user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields!",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        boolean checkFlat = DB.checkFlat(flatNo);
                        if(!checkFlat){
                            boolean insert = DB.insertUser(flatNo,user,pass);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"User Already Exists, Sign-in!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Passwords not matching!",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }
}