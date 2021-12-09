package com.example.studenthub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends Activity {

    Handler handler;
    ImageView splashimage;
    TextView splashtext;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_file);
        splashimage = findViewById(R.id.splashimage);
        splashtext = findViewById(R.id.splashtext);

        auth = FirebaseAuth.getInstance();

        Animation an = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        splashimage.startAnimation(an);
        splashtext.startAnimation(an);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (auth.getCurrentUser() !=null){
//            openHome();
//        }
//        else {
//           openLogin();
//        }
//    }
//
//    private void openLogin() {
//        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//        finish();
//    }
//
//    private void openHome() {
//        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
//        finish();
//    }
}
