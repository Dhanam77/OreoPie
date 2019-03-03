package com.example.oreopie;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    final public static int timeOut = 2000;
    private ImageView appLogo;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName = (TextView)findViewById(R.id.splashname);
        appLogo = (ImageView)findViewById(R.id.splashlogo);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.splash);
        appLogo.startAnimation(anim);
        appName.startAnimation(anim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this ,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        },timeOut);
    }
}
