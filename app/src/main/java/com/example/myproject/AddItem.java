package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class AddItem extends AppCompatActivity implements View.OnClickListener{


    TextInputLayout itemName,itemDetails, itemPrice, itemImage;
    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_item);

        itemName = findViewById(R.id.itemName);
        itemDetails = findViewById(R.id.itemDetails);
        itemPrice = findViewById(R.id.itemPrice);
        itemImage = findViewById(R.id.itemImage);
        addItem = findViewById(R.id.addItem);

        addItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}