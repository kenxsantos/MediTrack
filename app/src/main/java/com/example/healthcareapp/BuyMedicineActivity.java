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
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages = {
            {"Aspirin", "", "", "", "120"},
            {"Amoxicilin", "", "", "", "190"},
            {"Metformin", "", "", "", "90"},
            {"Losartan", "", "", "", "200"},
            {"Levothyroxine", "", "", "", "320"},
            {"Paracetamol", "", "", "", "80"},
            {"Omeprazole", "", "", "", "115"},
            {"Atorvastatin", "", "", "", "320 "},
    };
    private  String[] package_details = {
            "Aspirin, a commonly prescribed medication, is often recommended by physicians for its anti-inflammatory properties. \n" +
                    "It's frequently used to relieve mild to moderate pain, reduce fever, and prevent blood clotting, especially in patients at risk of cardiovascular diseases, as suggested by cardiologists.",
            "Amoxicillin is an antibiotic frequently prescribed by pediatricians to treat various bacterial infections in children, ranging from ear infections to urinary tract infections.",
            "Metformin is a medication commonly prescribed by endocrinologists to manage type 2 diabetes.\n" +
                    "It works by decreasing glucose production in the liver and improving insulin sensitivity in the body.",
            "Losartan is an angiotensin II receptor blocker (ARB) medication commonly prescribed by cardiologists to treat high blood pressure (hypertension) and heart failure, reducing the risk of heart attacks and strokes.",
            "Levothyroxine is a synthetic thyroid hormone frequently prescribed by endocrinologists to treat hypothyroidism, a condition where the thyroid gland does not produce enough hormones, helping regulate metabolism and energy levels.",
            " Paracetamol, also known as acetaminophen, is a common over-the-counter medication recommended by physicians for its analgesic (pain-relieving) and antipyretic (fever-reducing) properties. ",
            "Omeprazole is a proton pump inhibitor commonly prescribed by physicians to treat various gastrointestinal conditions such as acid reflux, ulcers, and gastroesophageal reflux disease (GERD).",
            "Atorvastatin is a statin medication frequently recommended by cardiologists to lower cholesterol levels and reduce the risk of cardiovascular events, such as heart attacks and strokes, by improving heart health."
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    ListView lst;
    Button btnBack, btnGoToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.buyMedListView);
        btnBack = findViewById(R.id.btnBuyMedBack);
        btnGoToCart = findViewById(R.id.btnBuyMedGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
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
                R.layout.multi_lines4,
                new String [] {"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                intent.putExtra("text1", packages[i][0]);
                intent.putExtra("text2", package_details[i]);
                intent.putExtra("text3", packages[i][4]);
                startActivity(intent);
            }
        });
    }
}