package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.myproject.drawerslider.Sliding;

public class Cart extends AppCompatActivity implements View.OnClickListener{
    Button placeOrder, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        placeOrder = findViewById(R.id.placeOrder);
        back = findViewById(R.id.back);
        placeOrder.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.placeOrder:
                Intent intent = new Intent(this, Thanks.class);
                startActivity(intent);
                finish();
                break;
            case R.id.back:
                Intent intent1 = new Intent(this, Sliding.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}