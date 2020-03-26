package com.example.mohammed_elagha.simplechatapp.chat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class ChatSample {
    DatabaseReference databaseReference;
    Message message;
    String UserId = "2yl1gBjyJ0e0G45X9Em7B5O5GKt1";
    String receiver = "Mbww8PIjgBVxeZUeGWZqUeBcZb42";

    public ChatSample(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void createMessage (String messageTxt) {
        message = new Message(UserId, receiver, messageTxt);
        makeChat();
    }

    public void makeChat () {
        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("sender", message.getSender());
        msgMap.put("receiver", message.getReceiver());
        msgMap.put("message", message.getMessage());

        MessageSender.sendMessage(databaseReference, msgMap);
    }
}
