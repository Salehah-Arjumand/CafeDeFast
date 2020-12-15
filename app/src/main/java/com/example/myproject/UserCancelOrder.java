package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class UserCancelOrder extends AppCompatActivity implements View.OnClickListener{

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cancel_order);
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(UserCancelOrder.this, Cancelled.class);
        startActivity(intent);
        finish();
    }
}