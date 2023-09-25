package com.example.arztin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginType extends AppCompatActivity {

    Button register,doctor_login;
    TextView login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);

        register = findViewById(R.id.register);
        doctor_login = findViewById(R.id.doctor_login);
        login_type = findViewById(R.id.login_patient);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginType.this,Registration.class);
                startActivity(intent);
            }
        });

        doctor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginType.this,DoctorLogin.class);
                startActivity(intent);
            }
        });

        login_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginType.this,Patient_Login.class);
                startActivity(intent);
            }
        });
    }
}