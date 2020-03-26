package com.example.mohammed_elagha.simplechatapp.auth;

import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class AuthenticationSample {
    public static String EMAIL;
    public static String PASSWORD;
    private FirebaseAuthentication firebaseAuthentication;
    public static boolean IS_AUTH;
    private FirebaseUser firebaseUser;

    public AuthenticationSample () {
        Random rand = new Random();

        EMAIL = "sample.email@email.com";
        PASSWORD = "sample.password";
        firebaseAuthentication = new FirebaseAuthentication();
        IS_AUTH = false;
    }

    public FirebaseUser makeAuth () {
        if (IS_AUTH) {
            firebaseAuthentication.signUp(EMAIL, PASSWORD);
        } else {
            firebaseAuthentication.signIn(EMAIL, PASSWORD);
        }

        if (firebaseAuthentication.isAuthSuccess()) {
            firebaseUser = firebaseAuthentication.getUser();
        }

        return firebaseUser;
    }
}
