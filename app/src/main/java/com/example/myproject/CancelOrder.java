package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class CancelOrder extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout orderNumber, reason;

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

      //  orderNumber = findViewById(R.id.orderNumber);
        reason = findViewById(R.id.reason);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}