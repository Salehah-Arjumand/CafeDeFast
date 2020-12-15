package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Orders extends AppCompatActivity implements View.OnClickListener {
    Button orderDone, cancelOrder;
    TextView orderInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderDone=findViewById(R.id.orderDone);
        cancelOrder=findViewById(R.id.cancelOrder);
        orderInfo=findViewById(R.id.orderInfo);

        orderDone.setOnClickListener(this);
        cancelOrder.setOnClickListener(this);



        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //String Image_Url = dataSnapshot.child("Photo").getValue().toString();
                //Picasso.with(MyProfile.this).load(Image_Url).into(profile);
                orderInfo.setText(dataSnapshot.child("Email").getValue().toString());


                String Gender = dataSnapshot.child("Gender").getValue().toString();

                String DOB,Date;
                DOB = dataSnapshot.child("DOB").getValue().toString();
                int dob_day = Integer.parseInt(DOB.split("/")[0]);
                int dob_month = Integer.parseInt(DOB.split("/")[1]);
                int dob_year = Integer.parseInt(DOB.split("/")[2]);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                java.util.Date currentdate = new Date();
                Date = df.format(currentdate).split(" ")[0];

                int day = Integer.parseInt(Date.split("/")[0]);
                int month = Integer.parseInt(Date.split("/")[1]);
                int year = Integer.parseInt(Date.split("/")[2]);

                int age = year - dob_year;

                if (month < dob_month)
                {
                    age--;
                }
                else if(month==dob_month)
                {
                    if (day < dob_day)
                    {
                        age--;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderDone:
                Intent i = new Intent(Orders.this, OrderReady.class);
                startActivity(i);
                break;
            case R.id.cancelOrder:
                Intent intent = new Intent(Orders.this, CancelOrder.class);
                startActivity(intent);
                break;
        }
    }
}