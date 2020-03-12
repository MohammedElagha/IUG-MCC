package com.example.mohammed_elagha.firebasestorageapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    StorageReference parentRef;
    StorageReference childRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 1);

        String file_path = "storage/emulated/0/Download/tesT_image.jpg";
        File f = new File(file_path);
        Uri file = Uri.fromFile(f);

        firebaseStorage = FirebaseStorage.getInstance();
        parentRef = firebaseStorage.getReference();

        Long newName = (System.currentTimeMillis()/1000);
        String fileLiveName =  newName.toString();
        String fileLivePath = "upload/images/" + fileLiveName;
        childRef = parentRef.child(fileLivePath);

        final UploadTask uploadTask = childRef.putFile(file);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL = childRef.getDownloadUrl().toString();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
