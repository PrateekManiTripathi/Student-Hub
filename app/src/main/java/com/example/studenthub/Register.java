package com.example.studenthub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    TextView login,txt1;
    EditText username,email,contact,password,conf_pswrd;
    Button btn_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        login=findViewById(R.id.login);
        txt1=findViewById(R.id.txt1);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        password=findViewById(R.id.password);
        conf_pswrd=findViewById(R.id.conf_pswrd);
        btn_register=findViewById(R.id.btn_register);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user = username.getText().toString().trim();
               String email1 = email.getText().toString().trim();
               String cont = contact.getText().toString().trim();
               String psswrd = password.getText().toString().trim();
               String con_pswrd = conf_pswrd.getText().toString().trim();

               if (TextUtils.isEmpty(user)){
                   Toast.makeText(Register.this, "Please Enter username", Toast.LENGTH_SHORT).show();
                   return;
               }if (TextUtils.isEmpty(email1)){
                   Toast.makeText(Register.this, "Please Enter email", Toast.LENGTH_SHORT).show();
                    return;
               }if (TextUtils.isEmpty(cont)){
                   Toast.makeText(Register.this, "Please Enter cont", Toast.LENGTH_SHORT).show();
                    return;
               }if (TextUtils.isEmpty(psswrd)){
                   Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
               }if (TextUtils.isEmpty(con_pswrd)){
                   Toast.makeText(Register.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
               }

               if (psswrd.length()<6){
                   Toast.makeText(Register.this, "Password should be atleast 6 digit", Toast.LENGTH_SHORT).show();
               }

               if (psswrd.equals(con_pswrd)){
                   firebaseAuth.createUserWithEmailAndPassword(email1,psswrd)
                           .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                               Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Register.this,LoginActivity.class));
            }
        });
    }
}
