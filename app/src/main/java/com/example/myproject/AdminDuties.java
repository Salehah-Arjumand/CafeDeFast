package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myproject.R;

public class AdminDuties extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout currentOrders, addCategory, updateCategory, deleteCategory, addItem, updateItem, deleteItem, availability, addAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_duties);

        currentOrders = findViewById(R.id.currentOrders);
        addCategory = findViewById(R.id.addCategory);
        updateCategory = findViewById(R.id.updateCategory);
        deleteCategory = findViewById(R.id.deleteCategory);
        addItem = findViewById(R.id.addItem);
        updateItem = findViewById(R.id.updateItem);
        deleteItem = findViewById(R.id.deleteItem);
        availability = findViewById(R.id.availability);
        addAmount = findViewById(R.id.addAmount);

        currentOrders.setOnClickListener(this);
        addCategory.setOnClickListener(this);
        updateCategory.setOnClickListener(this);
        deleteCategory.setOnClickListener(this);
        addItem.setOnClickListener(this);
        updateItem.setOnClickListener(this);
        deleteItem.setOnClickListener(this);
        availability.setOnClickListener(this);
        addAmount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.currentOrders:
                Intent i = new Intent(AdminDuties.this, Orders.class);
                startActivity(i);
                break;
            case R.id.addCategory:
                Intent intent = new Intent(AdminDuties.this, AddCategory.class);
                startActivity(intent);
                break;
            case R.id.updateCategory:
                Intent intent1 = new Intent(AdminDuties.this, UpdateCategory.class);
                startActivity(intent1);
                break;
            case R.id.deleteCategory:
                Intent intent2 = new Intent(AdminDuties.this, DeleteCategory.class);
                startActivity(intent2);
                break;
            case R.id.addItem:
                Intent intent3 = new Intent(AdminDuties.this, AddItem.class);
                startActivity(intent3);
                break;
            case R.id.updateItem:
                Intent intent4 = new Intent(AdminDuties.this, UpdateItem.class);
                startActivity(intent4);
                break;
            case R.id.deleteItem:
                Intent intent5 = new Intent(AdminDuties.this, DeleteItem.class);
                startActivity(intent5);
                break;
            case R.id.availability:
                Intent intent6 = new Intent(AdminDuties.this, Availability.class);
                startActivity(intent6);
                break;
            case R.id.addAmount:
                Intent intent7 = new Intent(AdminDuties.this, AddAmount.class);
                startActivity(intent7);
                break;
        }
    }
}