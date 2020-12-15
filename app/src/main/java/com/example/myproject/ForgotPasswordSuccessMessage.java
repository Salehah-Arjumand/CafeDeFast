package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ForgotPasswordSuccessMessage extends AppCompatActivity implements View.OnClickListener{

    Button callLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_success_message);
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        callLogin = findViewById(R.id.callLogin);

        callLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ForgotPasswordSuccessMessage.this, Login.class);
        startActivity(intent);
        finish();
    }
}