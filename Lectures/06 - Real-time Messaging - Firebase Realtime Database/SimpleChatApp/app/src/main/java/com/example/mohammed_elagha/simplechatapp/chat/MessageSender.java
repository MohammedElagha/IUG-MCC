package com.example.mohammed_elagha.simplechatapp.chat;

import com.example.mohammed_elagha.simplechatapp.MainActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

public class MessageSender {


    public static boolean sendMessage (DatabaseReference databaseReference, Map<String, Object> message) {
        boolean status = false;

        Task task = databaseReference.push().setValue(message);
        if (task.isSuccessful()) {
            status = true;
        }

        return status;
    }
}
