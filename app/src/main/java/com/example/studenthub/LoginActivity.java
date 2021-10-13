package com.example.studenthub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    TextView txt3,txt1,txt2;
    EditText et1,et2;
    Button btn1;
    private FirebaseAuth firebaseAuth;
    private ImageView signInButtonGoogle;
    private ImageView signInButtonFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        txt3=findViewById(R.id.txt3);
        txt2=findViewById(R.id.txt2);
        txt1=findViewById(R.id.txt1);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        btn1=findViewById(R.id.btn1);

        firebaseAuth = FirebaseAuth.getInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_log = et1.getText().toString().trim();
                String pswrd_log = et2.getText().toString().trim();

                if (TextUtils.isEmpty(email_log)) {
                    Toast.makeText(LoginActivity.this, "Please Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pswrd_log)) {
                    Toast.makeText(LoginActivity.this, "Please Enter cont", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pswrd_log.length()<6){
                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email_log,pswrd_log)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotActivity.class));
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Register.class));
            }
        });
        signInButtonGoogle=findViewById(R.id.imageGG);
        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent=new Intent(LoginActivity.this,GoogleSignInActivty.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        signInButtonFacebook=findViewById(R.id.imageFb);
        signInButtonFacebook.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(LoginActivity.this,FbSignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}
