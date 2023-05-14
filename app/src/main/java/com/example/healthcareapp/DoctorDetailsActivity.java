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
                    {"Ken Santos", "Chinese General Hospital", "5 years", "09485941263", "600"},
                    {"Jhaz Luna", "Metropolitan Hospital", "8 years", "09178561345", "800"},
                    {"Jeila Marcelo", "Mary Johnston Hospital", "6 years", "09466713845", "700"},
                    {"Albrecht Saribay", "Medical City", "6 years", "09456972358", "650"},
                    {"Carl Saguinsin", "Sacred Heart Hospital", "7 years", "09456974515", "820"},
            };
    private String [][] doctor_details2 =
            {
                    {"Ken Santos", "Chinese General Hospital", "5 years", "09485941263", "600"},
                    {"Jhaz Luna", "Metropolitan Hospital", "8 years", "09178561345", "800"},
                    {"Jeila Marcelo", "Mary Johnston Hospital", "6 years", "09466713845", "700"},
                    {"Albrecht Saribay", "Medical City", "6 years", "09456972358", "650"},
                    {"Carl Saguinsin", "Sacred Heart Hospital", "7 years", "09456974515", "820"},

            };
    private String [][] doctor_details3 =
            {
                    {"Ken Santos", "Chinese General Hospital", "5 years", "09485941263", "600"},
                    {"Jhaz Luna", "Metropolitan Hospital", "8 years", "09178561345", "800"},
                    {"Jeila Marcelo", "Mary Johnston Hospital", "6 years", "09466713845", "700"},
                    {"Albrecht Saribay", "Medical City", "6 years", "09456972358", "650"},
                    {"Carl Saguinsin", "Sacred Heart Hospital", "7 years", "09456974515", "820"},
            };
    private String [][] doctor_details4 =
            {
                    {"Ken Santos", "Chinese General Hospital", "5 years", "09485941263", "600"},
                    {"Jhaz Luna", "Metropolitan Hospital", "8 years", "09178561345", "800"},
                    {"Jeila Marcelo", "Mary Johnston Hospital", "6 years", "09466713845", "700"},
                    {"Albrecht Saribay", "Medical City", "6 years", "09456972358", "650"},
                    {"Carl Saguinsin", "Sacred Heart Hospital", "7 years", "09456974515", "820"},
            };
    private String [][] doctor_details5 =
            {
                    {"Ken Santos", "Chinese General Hospital", "5 years", "09485941263", "600"},
                    {"Jhaz Luna", "Metropolitan Hospital", "8 years", "09178561345", "800"},
                    {"Jeila Marcelo", "Mary Johnston Hospital", "6 years", "09466713845", "700"},
                    {"Albrecht Saribay", "Medical City", "6 years", "09456972358", "650"},
                    {"Carl Saguinsin", "Sacred Heart Hospital", "7 years", "09456974515", "820"},
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

        if(title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
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