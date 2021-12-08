package com.example.studenthub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.Login;

public class MainActivity extends AppCompatActivity {
    Button adminbutton,studbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminbutton = findViewById(R.id.adminbutton);
        studbutton = findViewById(R.id.studbutton);

        studbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }

    public void btn_showDialog(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box,null);

        final EditText txt_psswrd = mView.findViewById(R.id.txt_psswrd);
        final Button btn_cancel = mView.findViewById(R.id.btn_cancel);
        final Button btn_ok = mView.findViewById(R.id.btn_ok);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_psswrd.getText().toString().equals("12345")) {
                    //Toast.makeText(MainActivity.this, "Welcome to Login Page", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, UploadpdfFile.class));
                }else{
                    if (txt_psswrd.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                    }
                }
                alertDialog.dismiss();

//
            }
        });
        alertDialog.show();
    }


}