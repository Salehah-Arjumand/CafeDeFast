package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.UUID;

public class AddCategory extends AppCompatActivity implements View.OnClickListener {



    FirebaseDatabase firebaseDatabase;
    Button save,male,female,prefernottosay;
    CircularImageView Photo;
    ImageView camera_icon;
    TextView DOB;
    EditText FN,LN,Phone,Bio;
    String Image_Url;
    String Gender;
    Drawable simple,selected;
    Calendar calender;
    DatePickerDialog.OnDateSetListener date;

    TextInputLayout categoryName, categoryImage;
    Button addCategory;
    ImageView UploadPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Image_Url=null;
        //to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_category);

        categoryName = findViewById(R.id.categoryName);
        categoryImage = findViewById(R.id.categoryImage);
        addCategory = findViewById(R.id.addCategory);
        UploadPhoto = findViewById(R.id.UploadPhoto);


        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Gender")) {
                    Image_Url = dataSnapshot.child("Photo").getValue().toString();
                    Picasso.with(AddCategory.this).load(Image_Url).into(UploadPhoto);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });


        addCategory.setOnClickListener(this);



        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    if( Image_Url!=null)
                    {

                        DatabaseReference temp= firebaseDatabase.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        temp.child("categoryName").setValue(categoryName.getEditText().getText().toString());
                        temp.child("Photo").setValue(Image_Url);




                        Intent i=new Intent(AddCategory.this,AdminDuties.class);
                        startActivity(i);

                    }
                    else if(Image_Url==null)
                    {
                        Toast.makeText(AddCategory.this,"Kindly select your photo",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }




    @Override
    public void onClick(View view) {

    }




    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            uploadImage(imageUri);

        }
    }
    private void uploadImage(Uri filePath) {

        if(filePath != null)
        {
            final StorageReference ref = FirebaseStorage.getInstance().getReference().child("Images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String s2 = taskSnapshot.getUploadSessionUri().toString();
                            String[] s = s2.split("/o") ;

                            String image_name = taskSnapshot.getMetadata().getName();

                            String image_url = s[0]+"/o/Images%2F"+image_name+"?alt=media";

                            Picasso.with(AddCategory.this).load(image_url).into(UploadPhoto);

                            Image_Url = image_url;
                        }
                    });
        }
    }

}