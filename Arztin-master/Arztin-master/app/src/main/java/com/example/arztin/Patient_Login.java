package com.example.arztin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Patient_Login extends AppCompatActivity {

    EditText email_login,password_login;
    Button login;
    TextView forgot_password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        email_login = findViewById(R.id.Email_login);
        password_login = findViewById(R.id.password_login);
        login = findViewById(R.id.LogBtn);
        fAuth = FirebaseAuth.getInstance();
        forgot_password = findViewById(R.id.forgotpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String memail = email_login.getText().toString().trim();
                String mpassword = password_login.getText().toString().trim();

                if (TextUtils.isEmpty(memail))
                {
                    email_login.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(mpassword))
                {
                    password_login.setError("Password is Required");
                    return;
                }

                if (mpassword.length()<6)
                {
                    password_login.setError("Password must be greater than 6 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Patient_Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Patient_Login.this, "Error !!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText restMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialogue =  new AlertDialog.Builder(view.getContext());
                passwordResetDialogue.setTitle("Reset Password ?");
                passwordResetDialogue.setMessage("Enter Your Email");
                passwordResetDialogue.setView(restMail);

                passwordResetDialogue.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String mail = restMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Patient_Login.this, "Reset Link Sent to Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Patient_Login.this, "Error ! Link is Not Sent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialogue.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialogue.create().show();
            }
        });
    }
}