package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class AddAmount extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout userRollNo, amount;
    Button addAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_amount);

        userRollNo = findViewById(R.id.userRollNo);
        amount = findViewById(R.id.amount);
        addAmount = findViewById(R.id.addAmount);

        addAmount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        
    }
}