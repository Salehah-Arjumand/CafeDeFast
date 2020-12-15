package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproject.drawerslider.Sliding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout adminId, adminPassword;
    Button adminLogin, callAdminSignup, forgotPassword;
    private FirebaseAuth mAuth;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_login);

        adminId = findViewById(R.id.adminId);
        adminPassword = findViewById(R.id.adminPassword);
        forgotPassword = findViewById(R.id.forgotpassword);
        adminLogin = findViewById(R.id.adminLogin);
        callAdminSignup = findViewById(R.id.callAdminSignup);

        forgotPassword.setOnClickListener(this);
        adminLogin.setOnClickListener(this);
        callAdminSignup.setOnClickListener(this);


        adminLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here

                String email = adminId.getEditText().getText().toString();
                String password = adminPassword.getEditText().getText().toString();
                if (adminId.getEditText().getText().toString().length() == 0 || adminPassword.getEditText().getText().toString().length() == 0) {
                    Toast.makeText(AdminLogin.this, "Kindly enter username and password.",
                            Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("Gender")) {
                                                    Intent i = new Intent(AdminLogin.this, AdminDuties.class);
                                                    startActivity(i);
                                                } else {
                                                    Intent i = new Intent(AdminLogin.this, AdminDuties.class);
                                                    startActivity(i);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        task.addOnFailureListener(AdminLogin.this, new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                if (e.getMessage().contains("password is invalid")) {
                                                    Toast.makeText(AdminLogin.this, "Password does not match with user account.",
                                                            Toast.LENGTH_LONG).show();
                                                } else if (e.getMessage().contains("There is no user record")) {
                                                    Toast.makeText(AdminLogin.this, "User account not registered.",
                                                            Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(AdminLogin.this, e.getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    }
                                }
                            });
                }
            }
        });



//        public void onClick (View view){
//            switch (view.getId()) {
//                case R.id.forgotpassword:
//                    Intent intent2 = new Intent(AdminLogin.this, ForgotPassword.class);
//                    startActivity(intent2);
//                    finish();
//                    break;
//                case R.id.adminLogin:
//                    Intent intent = new Intent(AdminLogin.this, AdminDuties.class);
//                    startActivity(intent);
//                    break;
//                case R.id.callAdminSignup:
//                    Intent intent1 = new Intent(AdminLogin.this, AdminSignup.class);
//                    startActivity(intent1);
//                    break;
//            }
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgotpassword:
                Intent intent2 = new Intent(AdminLogin.this, ForgotPassword.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.adminLogin:
                Intent intent = new Intent(AdminLogin.this, AdminDuties.class);
                startActivity(intent);
                break;
            case R.id.callAdminSignup:
                Intent intent1 = new Intent(AdminLogin.this, AdminSignup.class);
                startActivity(intent1);
                break;
        }

    }
}