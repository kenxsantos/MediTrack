package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String [][] doctor_details1 =
            {
                    {"Dr. Miguel Santos Cruz", "A101", "15 years", "+63 912 345 6789", "1,200"},
                    {"Dra. Maria Luisa Gonzales", "A102", "10 years", "+63 922 987 6543", "1,000"},
                    {"Dr. Ricardo Gonzales Reyes", "A103", "6 years", "+63 933 456 7890", "700"},
                    {"Dra. Sofia Ramirez Santos", "A104", "8 years", "+63 944 321 0987", "850"},
            };
    private String [][] doctor_details2 =
            {
                    {"Dra. Andrea Gonzales Santos", "D401", "5 years", "+63 966 543 2109", "1,000"},
                    {"Dr. Juan Carlos Santos Garcia", "D402", "8 years", " +63 977 890 1234", "1,500"},
                    {"Dra. Gabriela Torre", "D403", "7 years", "+63 988 765 4321", "1,300"},
            };
    private String [][] doctor_details3 =
            {
                    {"Dra. Angela Lim", "B201", "5 years", "+63 911 876 5432", "800"},
                    {"Dra. Sofia Ramirez", "B202", "8 years", "+63 933 987 6543", "1,000"},
                    {"Dr. Emilio Cruz Jr.", "B204", "3 years", "+63 944 456 7890", "600"},
                    {"Dr. Carla Santos Reyes", "B205", "4 years", "+63 955 321 0987", "750"},
            };
    private String [][] doctor_details4 =
            {
                    {"Dra. Rosario Garcia", "E501", "5 years", "+63 911 234 5678", "600"},
                    {"Dr. Andres Mercado", "E502", "8 years", "+63 922 876 5432", "800"},
            };
    private String [][] doctor_details5 =
            {
                    {"Dra. Valentina Reyes Gonzales", "C301", "10 years", "+63 955 456 7890", "1,250"},
                    {"Dr. Juan Carlos Jr.", "C302", "8 years", "+63 966 321 0987", "1,000"},
                    {"Dra. Aurora Delos Reyes", "C303", "11 years", "+63 977 678 1234", "1,500"},
            };
    TextView textview;
    Button btnBack;
    String [][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details_acitivity);

        textview = findViewById(R.id.textViewDDTitle);
        btnBack = findViewById(R.id.buttonDDBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textview.setText(title);

        if(title.compareTo("Physician")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Pediatrician")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Endocrologists")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put( "line1", doctor_details[i][0]);
            item.put( "line2", doctor_details[i][1]);
            item.put( "line3", doctor_details[i][2]);
            item.put( "line4", doctor_details[i][3]);
            item.put( "line5", "Cons Fees: " + doctor_details[i][4] + "/-");
            list.add(item);
        }
        simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst =  findViewById(R.id.listviewDD);
        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                intent.putExtra( "text1", title);
                intent.putExtra( "text2", doctor_details[i][0]);
                intent.putExtra( "text3", doctor_details[i][1]);
                intent.putExtra( "text4", doctor_details[i][3]);
                intent.putExtra( "text5", doctor_details[i][4]);
                startActivity(intent);
            }
        });
    }
}