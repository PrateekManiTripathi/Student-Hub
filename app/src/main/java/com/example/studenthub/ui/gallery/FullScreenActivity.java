package com.example.studenthub.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.studenthub.R;

public class FullScreenActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("Full Screen");
        imageView=(ImageView)findViewById(R.id.gallery_image);
        Intent i=getIntent();
        int position = i.getExtras().getInt("id");
        GridAdapter gridAdapter=new GridAdapter(this);
        imageView.setImageResource(gridAdapter.imageArray[position]);
    }
}