package com.example.mohammed_elagha.simplechatapp.chat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class MessageSender {
    public static final String FIREBASE_DATABASE_CHILD_PATH = "chats";

    public static boolean sendMessage (DatabaseReference databaseReference, Map<String, Object> message) {
        boolean status = false;

        Task task = databaseReference.child(FIREBASE_DATABASE_CHILD_PATH).push().setValue(message);
        if (task.isSuccessful()) {
            status = true;
        }

        return status;
    }
}
