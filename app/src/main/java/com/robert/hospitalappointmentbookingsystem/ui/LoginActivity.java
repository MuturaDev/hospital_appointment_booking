package com.robert.hospitalappointmentbookingsystem.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.robert.hospitalappointmentbookingsystem.R;
import com.robert.hospitalappointmentbookingsystem.Utils.Utils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText txt_password;
    private EditText txt_username;

    private TextView password_error;
    private TextView username_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_password = findViewById(R.id.edit_password);
        txt_username = findViewById(R.id.txt_username);

        username_error = findViewById(R.id.username_error);
        password_error = findViewById(R.id.password_error);

        //TODO: REMOVE THIS IN PRODUCTION
        int  i = 1/0;


        ToggleButton toggleButton = findViewById(R.id.toggle_password_visible);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    txt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }


    public void login_btn(View view) {


        boolean moveon = true;

        if (!Utils.checkForValidName(txt_username.getText().toString())) {
            username_error.setVisibility(View.VISIBLE);
            moveon = false;
        } else {
            username_error.setVisibility(View.GONE);
        }

        if (!Utils.checkForValidName(txt_password.getText().toString())) {
            password_error.setVisibility(View.VISIBLE);
            moveon = false;
        } else {
            password_error.setVisibility(View.GONE);
        }


        if (moveon) {
            SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.colorPrimary);
            pDialog.setTitleText("Please wait for confirmation...");
            pDialog.setCancelable(false);
            pDialog.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.cancel();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    if (txt_username.getText().toString().equalsIgnoreCase("doctor") &&
                            txt_password.getText().toString().equalsIgnoreCase("123456")) {
                        intent.putExtra("Home", true);
                        startActivity(intent);
                        finish();
                    }else if (txt_username.getText().toString().equalsIgnoreCase("patient") &&
                            txt_password.getText().toString().equalsIgnoreCase("123456")) {
                        intent.putExtra("Home", false);
                        startActivity(intent);
                        finish();

                    }else if (txt_username.getText().toString().equalsIgnoreCase("admin") &&
                            txt_password.getText().toString().equalsIgnoreCase("123456")) {
                        intent.putExtra("Home", false);
                        startActivity(intent);
                        finish();

                    }else{
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Please ensure your username and password are correct!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                    }
                                })
                                .show();
                    }

                }
            }, 3000);
        }

    }

    public void signup_btn(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        // finish();
    }


}
