package com.example.arztin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity {

    EditText doc_email,doc_pass;
    Button doc_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        doc_email = findViewById(R.id.doc_email);
        doc_pass = findViewById(R.id.doc_pass);
        doc_log = findViewById(R.id.doc_log);

        doc_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String demail = doc_email.getText().toString();
                String dpass = doc_pass.getText().toString();

                if(TextUtils.isEmpty(demail))
                {
                    doc_email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(dpass))
                {
                    doc_pass.setError("Password is Required");
                    return;
                }

                if (demail.equals("arztin@gmail.com") && dpass.equals("Capstone"))
                {
                    Intent intent = new Intent(DoctorLogin.this,Doctor_main.class);
                    startActivity(intent);

                    Toast.makeText(DoctorLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DoctorLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}