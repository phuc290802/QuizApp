package com.example.appquiz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.appquiz.R;
import com.example.appquiz.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private TextView appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);

        appname=findViewById(R.id.app_name);

        Typeface typeface= ResourcesCompat.getFont(this,R.font.blacklist);
        appname.setTypeface(typeface);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.myanim);
        appname.setAnimation(anim);

        new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }.start();
    }
}