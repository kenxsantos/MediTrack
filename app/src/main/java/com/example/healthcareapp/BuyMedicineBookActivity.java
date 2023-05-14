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

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText inputName, inputAddress, inputPin, inputContact;
    Button btnBook, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        inputName = findViewById(R.id.buyMedBookFullName);
        inputAddress = findViewById(R.id.buyMedBookAddress);
        inputPin  = findViewById(R.id.buyMedBookPinCode);
        inputContact  = findViewById(R.id.buyMedBookContact);
        btnBook = findViewById(R.id.btnBuyMedBookBook);
        btnBack = findViewById(R.id.btnBuyMedBookBack);


        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String bookDate = intent.getStringExtra("date");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                String fullName = inputName.getText().toString();
                String address = inputAddress.getText().toString();
                String pin = inputPin.getText().toString();
                String contact = inputContact.getText().toString();



                if (fullName.length() == 0 || address.length() == 0 || pin.length() == 0 || contact.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details!", Toast.LENGTH_SHORT).show();
                }else{
                    int pinCode = Integer.parseInt(inputPin.getText().toString());
                    String date = bookDate.toString();
                    db.addOrder(username, fullName, address, pinCode, contact, date,"", Float.parseFloat(price[1].toString()), "medicine");
                    db.removeCart(username,"medicine");
                    Toast.makeText(getApplicationContext(), "Booking Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineBookActivity.this, CartBuyMedicineActivity.class));
            }
        });

    }
}