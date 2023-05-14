package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LabTestActivity extends AppCompatActivity {

    private String[][]  packages =
            {
                    {"Full Body CheckUp", "", "", "", "999"},
                    {"Blood Glucose Fasting", "", "", "", "299"},
                    {"COVID-19 Antibody - IgG", "", "", "", "99"},
                    {"Thyroid Check", "", "", "", "599"},
                    {"Immunity Check", "", "", "", "799"},
            };

    private String[] package_details =
            {
                    "Blood Glucose Fasting\n" +
                            " Complete Hemogram\n" +
                            "HbA1c\n" +
                            " Iron Studies\n"+
                            "Kidney Function Test" +
                            "LDH Lactate Dehydrogenase, Serum\n" +
                            "Lipid Profile\n" +
                            "Liver Function Test",
                    "Blood Glucose Fasting ",
                    "COVID-19 Antibody - IgG",
                    "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
                    "Complete Hemogram\n" +
                            "CRP (C Reactive Protein) Quantitative, Serum\n" +
                            " Iron Studies\n" +
                            "Kidney Function Test\n" +
                            "Vitamin D Total-25 Hydroxy\n" +
                            "Liver Function Test\n" +
                            "Lipid Profile"
            };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    Button btnGoToCart, btnLTBack;
    ListView listViewLT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.btnLTCart);
        btnLTBack  = findViewById(R.id.btnLTBack);
        listViewLT = findViewById(R.id.listViewLT);

        btnLTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++ ){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }
        simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines2,
                new String [] {"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listViewLT.setAdapter(simpleAdapter);

        listViewLT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                intent.putExtra("text1", packages[i][0]);
                intent.putExtra("text2", package_details[i]);
                intent.putExtra("text3", packages[i][4]);
                startActivity(intent);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}