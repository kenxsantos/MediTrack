package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText inputUsername, inputPassword;
    Button btn;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.loginUsername);
        inputPassword = findViewById(R.id.loginPassword);
        btn = findViewById(R.id.loginButton);
        reg = findViewById(R.id.register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details!", Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        //to save data and with key value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

}