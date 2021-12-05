package com.example.studenthub.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studenthub.R;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Full Screen");
    }
}