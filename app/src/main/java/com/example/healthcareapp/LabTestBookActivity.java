package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText inputName, inputAddress, inputPin, inputContact;
    Button btnBook, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        inputName = findViewById(R.id.labTestBookFullName);
        inputAddress = findViewById(R.id.labTestBookAddress);
        inputPin  = findViewById(R.id.labTestBookPinCode);
        inputContact  = findViewById(R.id.labTestBookContact);
        btnBook = findViewById(R.id.btnLabTestBook);
        btnBack = findViewById(R.id.btnLabTestBack);


        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String bookDate = intent.getStringExtra("date");
        String bookTime = intent.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences  sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                String fullName = inputName.getText().toString();
                String address = inputAddress.getText().toString();
                String contact = inputContact.getText().toString();
                String pin = inputPin.getText().toString();


                if (fullName.length() == 0 || address.length() == 0 || pin.length() == 0 || contact.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details!", Toast.LENGTH_SHORT).show();
                }else{
                    String date = bookDate.toString();
                    String time = bookTime.toString();
                    int pinCode = Integer.parseInt(inputPin.getText().toString());

                    db.addOrder(username, fullName, address, pinCode, contact, date, time, Float.parseFloat(price[1].toString()), "lab");
                    db.removeCart(username,"lab");
                    Toast.makeText(getApplicationContext(), "Booked Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
              }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestBookActivity.this, CartLabActivity.class));
            }
        });

    }
}