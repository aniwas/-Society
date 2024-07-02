package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayAmount extends AppCompatActivity {
    Button payButton;
    EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_amount);

        amount = (EditText) findViewById(R.id.amountEditText);
        payButton = (Button) findViewById(R.id.payButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = amount.getText().toString();

                // Use Google Pay API to initiate payment
                // Construct intent to open Google Pay with the specified amount and recipient number
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("upi://pay?pa=1234567890@upi&pn=ApartmentTreasurer&tn=MonthlyMaintainanceFee&am=" + amt + "&cu=INR"));
                startActivity(intent);

            }
        });


    }
}