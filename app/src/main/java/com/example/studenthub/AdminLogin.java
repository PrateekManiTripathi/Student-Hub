package com.example.studenthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText txt1,txt2;
    Button loginBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        txt1=findViewById(R.id.et1);
        txt2=findViewById(R.id.et2);
        loginBtn=findViewById(R.id.btn1);
        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_log = txt1.getText().toString().trim();
                String pswrd_log = txt2.getText().toString().trim();

                if (TextUtils.isEmpty(email_log)) {
                    Toast.makeText(AdminLogin.this, "Please Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pswrd_log)) {
                    Toast.makeText(AdminLogin.this, "Please Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pswrd_log.length()<6){
                    Toast.makeText(AdminLogin.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email_log,pswrd_log)
                        .addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(AdminLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),UploadpdfFile.class));
                                }
                                else{
                                    Toast.makeText(AdminLogin.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}