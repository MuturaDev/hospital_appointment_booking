package com.robert.hospitalappointmentbookingsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.robert.hospitalappointmentbookingsystem.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void login_btn(View view){
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
       // finish();
    }

    public void signup_btn(View view){
        startActivity(new Intent(SplashScreenActivity.this, SignupActivity.class));
       // finish();
    }
}
