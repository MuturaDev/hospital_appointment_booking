package com.robert.hospitalappointmentbookingsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.robert.hospitalappointmentbookingsystem.R;
import com.robert.hospitalappointmentbookingsystem.Utils.Utils;

public class SignupActivity extends AppCompatActivity {

    private EditText txt_password;
    private EditText txt_username;
    private EditText txt_email;
    private EditText txt_confirmpassword;

    private TextView password_error;
    private TextView username_error;
    private TextView email_error;
    private TextView confirmPassword_error;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt_password = findViewById(R.id.edit_password);
        txt_username = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);
        txt_confirmpassword = findViewById(R.id.txt_confirmpassword);

        password_error = findViewById(R.id.password_error);
        username_error = findViewById(R.id.username_error);
        email_error = findViewById(R.id.email_error);
        confirmPassword_error = findViewById(R.id.confirmpassword_error);



        ToggleButton toggleButton = findViewById(R.id.toggle_password_visible);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    txt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        ToggleButton toggleButton2 = findViewById(R.id.toggle_password_visible2);
        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txt_confirmpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    txt_confirmpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


    public void signup_btn(View view){

        boolean moveon = true;

        if(!Utils.checkForValidName(txt_username.getText().toString())){
            username_error.setVisibility(View.VISIBLE);
            moveon = false;
        }else{
            username_error.setVisibility(View.GONE);

        }

        if(!Utils.checkForValidName(txt_email.getText().toString())){
            email_error.setVisibility(View.VISIBLE);
            moveon = false;
        }else{
            email_error.setVisibility(View.GONE);
        }

        if(!Utils.checkForValidName(txt_password.getText().toString())){
            password_error.setVisibility(View.VISIBLE);
            moveon = false;
        }else{
            password_error.setVisibility(View.GONE);
        }

        if(!Utils.checkForValidName(txt_confirmpassword.getText().toString())){
            confirmPassword_error.setVisibility(View.VISIBLE);
            moveon = false;
        }else{
            confirmPassword_error.setVisibility(View.GONE);
        }


        if(moveon){
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.putExtra("Home", false);
            startActivity(intent);
            finish();
        }



    }

}
