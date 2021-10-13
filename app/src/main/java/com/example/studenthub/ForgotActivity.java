package com.example.studenthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private EditText txt_email;
    private Button forgot_btn;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        txt_email = findViewById(R.id.email_forgot);
        forgot_btn = findViewById(R.id.forgot_btn);

        auth = FirebaseAuth.getInstance();

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


    }

    private void validateData() {
        email = txt_email.getText().toString();
        if (email.isEmpty()){
            txt_email.setError("Required");
        }else{
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotActivity.this, "Check your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ForgotActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}