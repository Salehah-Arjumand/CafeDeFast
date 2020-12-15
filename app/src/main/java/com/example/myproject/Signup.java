package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity  {

    TextInputLayout name, rollNumber, email, phone, password;
    private FirebaseAuth mAuth;
    Button signup, callLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        rollNumber = findViewById(R.id.rollNumber);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);

        signup = findViewById(R.id.signup);
        callLogin = findViewById(R.id.callLogin);

//       signup.setOnClickListener(this);
//      callLogin.setOnClickListener(this);

        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if(email.getEditText().getText().toString().length()==0 ||  password.getEditText().getText().toString().length()==0 )
                {
                    Toast.makeText(Signup.this, "Kindlt Fill Email And Password.", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String email2 = email.getEditText().getText().toString();
                    String password2 =  password.getEditText().getText().toString();


                    mAuth.createUserWithEmailAndPassword(email2, password2)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Signup.this, "User "+user.getEmail()+" registered successfully.",
                                                Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(Signup.this, Login.class);
                                        startActivity(i);

                                    }
                                    else {
                                        // If sign in fails, display a message to the user.
                                        task.addOnFailureListener(Signup.this, new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(Signup.this, e.getMessage(),
//                                                        Toast.LENGTH_LONG).show();
                                                Toast.makeText(Signup.this, "User Not registered successfully.",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }
                                }
                            });

                }
            }
        });



    }

    private boolean validateName(){
        String value = name.getEditText().getText().toString();

        if(value.isEmpty()){
            name.setError("Field can not be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    private boolean validateRollNumber(){
        String value = rollNumber.getEditText().getText().toString();

        if(value.length()!=7){
            name.setError("Enter roll number in the right format i.e. i170051");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        String value = name.getEditText().getText().toString();

        if(value.isEmpty()){
            name.setError("Field can not be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    private boolean validatePhone(){
        String value = name.getEditText().getText().toString();

        if(value.isEmpty()){
            name.setError("Field can not be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String value = name.getEditText().getText().toString();

        if(value.isEmpty()){
            name.setError("Field can not be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }


}