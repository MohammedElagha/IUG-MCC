package com.example.mohammed_elagha.simplechatapp.auth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public class FirebaseAuthentication {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private boolean authStatus;

    public FirebaseAuthentication() {
        mAuth = FirebaseAuth.getInstance();
        authStatus = false;
    }

    // for only one time
    public void signUp (String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            authStatus = true;
                        }
                    }
                });
    }

    public void signIn (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            authStatus = true;
                        }
                    }
                });
    }

    public boolean isAuthSuccess () {
        return authStatus;
    }

    public FirebaseUser getUser () {
        return user;
    }
}
