package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.drawerslider.Sliding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout email_input, password;
    Button forgotPassword, callSignup, login, callAdminLogin;
    private FirebaseAuth mAuth;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        ctx = this;

        email_input = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = findViewById(R.id.forgotpassword);
        login = findViewById(R.id.login);
        callSignup = findViewById(R.id.callSignup);
        callAdminLogin = findViewById(R.id.callAdminLogin);

        forgotPassword.setOnClickListener(this);
        login.setOnClickListener(this);
        callSignup.setOnClickListener(this);
        callAdminLogin.setOnClickListener(this);


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here

                String email = email_input.getEditText().getText().toString();
                String password2 = password.getEditText().getText().toString();
                if (email_input.getEditText().length() == 0 || password.getEditText().length() == 0) {
                    Toast.makeText(Login.this, "Kindly enter roll number and password.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password2)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.hasChild("Gender"))
                                                {
                                                    Intent i = new Intent(Login.this, Sliding.class);
                                                    startActivity(i);
                                                }
                                                else
                                                {
                                                   Intent i = new Intent(Login.this, Sliding.class);
                                                     startActivity(i);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        task.addOnFailureListener(Login.this, new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                if(e.getMessage().contains("password is invalid")) {
                                                    Toast.makeText(Login.this, "Password does not match with user account.",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                else if (e.getMessage().contains("There is no user record")) {
                                                    Toast.makeText(Login.this, "User account not registered.",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    Toast.makeText(Login.this, e.getMessage(),
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




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgotpassword:
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
                finish();
                break;
            case R.id.callSignup:
                Intent intent1 = new Intent(Login.this, Signup.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.login:
                Intent intent2 = new Intent(Login.this, Sliding.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.callAdminLogin:
                Intent intent3 = new Intent(Login.this, AdminLogin.class);
                startActivity(intent3);
                finish();
                break;
        }
    }
}