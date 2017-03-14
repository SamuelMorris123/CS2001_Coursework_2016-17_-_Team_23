package com.sammorris.groupforums;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;

import static android.R.attr.data;

public class Post_Activity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mCatTitle;
    private EditText mCatDesc;
    private Button mSubmitBtn;
    private ProgressDialog mProgressBar;

    private Uri mImageUri;

    private static final int Gallery_Request = 1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Category");

        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);

        mCatTitle = (EditText) findViewById(R.id.Title);
        mCatDesc = (EditText) findViewById(R.id.DescField);

        mSubmitBtn = (Button) findViewById(R.id.Submit);

        mProgressBar = new ProgressDialog(this);


        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Request);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                startPosting();
            }
        });
    }

    private void startPosting() {

        mProgressBar.setMessage("Uploading Category");


        final String title_val = mCatTitle.getText().toString().trim();
        final String desc_val = mCatDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri != null);

        mProgressBar.show();

        StorageReference filepath = mStorage.child("Cat_Images").child(mImageUri.getLastPathSegment());
        filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                DatabaseReference newCat = mDatabase.push();

                newCat.child("Title").setValue(title_val);
                newCat.child("Desc").setValue(desc_val);
                newCat.child("image").setValue(downloadUrl.toString());

                mProgressBar.dismiss();
            }
        });
    }

    @Override
                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == Gallery_Request && resultCode == RESULT_OK){

                mImageUri = data.getData();

                mSelectImage.setImageURI(mImageUri);
            }
        }
    }

