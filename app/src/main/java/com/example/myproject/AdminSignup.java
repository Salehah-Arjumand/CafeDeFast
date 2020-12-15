package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminSignup extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    TextInputLayout adminName, empId, adminEmail, adminPhone, adminPassword;

    Button callAdminLogin, adminSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_signup);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        adminName = findViewById(R.id.adminName);
        empId = findViewById(R.id.empId);
        adminEmail = findViewById(R.id.adminEmail);
        adminPhone = findViewById(R.id.adminPhone);
        adminPassword = findViewById(R.id.adminPassword);

        callAdminLogin = findViewById(R.id.callAdminLogin);
        adminSignup = findViewById(R.id.adminSignup);


        adminSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if(adminEmail.getEditText().getText().toString().length()==0 ||  adminPassword.getEditText().getText().toString().length()==0 )
                {
                    Toast.makeText(AdminSignup.this, "Kindly Fill Email And Password.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String email =  adminEmail.getEditText().getText().toString();
                    String password =  adminPassword.getEditText().getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AdminSignup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(AdminSignup.this, "User "+user.getEmail()+" registered successfully.",
                                                Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(AdminSignup.this,AdminDuties.class);
                                        startActivity(i);

                                    }
                                    else {
                                        // If sign in fails, display a message to the user.
                                        task.addOnFailureListener(AdminSignup.this, new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(AdminSignup.this, e.getMessage(),
//                                                        Toast.LENGTH_LONG).show();
                                                Toast.makeText(AdminSignup.this, "Admin not register successfully.",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }
                                }
                            });

                }
            }
        });








       // adminSignup.setOnClickListener(this);
        callAdminLogin.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.callAdminLogin:
                Intent intent = new Intent(AdminSignup.this, AdminLogin.class);
                startActivity(intent);
                break;

            case R.id.adminSignup:
                Intent intent1 = new Intent(AdminSignup.this, AdminDuties.class);
                startActivity(intent1);
                break;
        }
    }
}