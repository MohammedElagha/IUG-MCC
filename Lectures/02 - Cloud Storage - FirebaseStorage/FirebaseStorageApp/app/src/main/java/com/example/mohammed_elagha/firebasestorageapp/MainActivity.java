package com.example.mohammed_elagha.firebasestorageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference uploadsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Uri file = Uri.fromFile(new File("/storage/emulated/0/Download/test_image.jpg"));
        uploadsRef = storageReference.child("uploads/test_image.jpg");

        if (new File("/storage/emulated/0/Download/test_image.jpg").exists()) {
            Toast.makeText(MainActivity.this, "FOUND", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "NOT FOUND", Toast.LENGTH_LONG).show();
        }

        UploadTask uploadTask = uploadsRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL = uploadsRef.getDownloadUrl().toString();
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
            }
        });

        // Pause the upload
        // uploadTask.pause();

        // Resume the upload
        // uploadTask.resume();

        // Cancel the upload
        // uploadTask.cancel();
    }
}
