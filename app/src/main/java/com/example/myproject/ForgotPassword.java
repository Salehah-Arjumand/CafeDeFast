package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout phone;
    CountryCodePicker country_code_picker;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        phone = findViewById(R.id.phone);
        next = findViewById(R.id.next);

        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ForgotPassword.this, MakeSelection.class);
        startActivity(intent);
        finish();
    }
}