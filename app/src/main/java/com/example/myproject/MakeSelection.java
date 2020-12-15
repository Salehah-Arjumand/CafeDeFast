package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MakeSelection extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout vieEmail, vieSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        vieEmail = findViewById(R.id.vieEmail);
        vieSms =findViewById(R.id.vieSms);

        vieEmail.setOnClickListener(this);
        vieSms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vieEmail:
                Intent intent = new Intent(MakeSelection.this, Verify.class);
                startActivity(intent);
                finish();
                break;
            case R.id.vieSms:
                Intent intent1 = new Intent(MakeSelection.this, Verify.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}