package com.example.studenthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class PhoneLogin extends AppCompatActivity {

    private CountryCodePicker ccp;
    private EditText enter_mobile;
    private Button get_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        ccp = findViewById(R.id.ccp);
        enter_mobile = findViewById(R.id.enter_mobile);
        get_otp = findViewById(R.id.get_otp);
        ccp.registerCarrierNumberEditText(enter_mobile);

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneLogin.this,ManageOtp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });
    }
}