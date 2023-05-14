package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText name, add, con, fee;
    Button dateButton, timeButton, btnBack, btnApp;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appoinment);

        tv = findViewById(R.id.bookTitle);

        name = findViewById(R.id.bookFullName);
        add = findViewById(R.id.bookHospitalAddress);
        con = findViewById(R.id.bookContactNo);
        fee = findViewById(R.id.bookFeesAmount);

        dateButton = findViewById(R.id.btnBookDate);
        timeButton = findViewById(R.id.btnBookTime);

        btnBack = findViewById(R.id.btnBookBack);
        btnApp = findViewById(R.id.btnAppointment);

        name.setKeyListener(null);
        add.setKeyListener(null);
        con.setKeyListener(null);
        fee.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullName = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String fees = intent.getStringExtra("text5");

        tv.setText(title);
        name.setText(fullName);
        add.setText(address);
        con.setText(contact);
        fee.setText(fees);

        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        //timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                SharedPreferences sharedPreferences  = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                String date = dateButton.getText().toString();
                String time = timeButton.getText().toString();
                if (db.checkAppointmentExist(username, title + " : " + fullName, address, contact, date, time)==1){
                    Toast.makeText(getApplicationContext(), "Appointment Already Booked!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addOrder(username, title + " : " + fullName, address, 0, contact, date, time, Float.parseFloat(fees), "appointment" );
                    Toast.makeText(getApplicationContext(), "Appointment Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 +1;
                dateButton.setText(i2+"/" + i1 + "/" + i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i + ":" + i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog  = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);


    }
}