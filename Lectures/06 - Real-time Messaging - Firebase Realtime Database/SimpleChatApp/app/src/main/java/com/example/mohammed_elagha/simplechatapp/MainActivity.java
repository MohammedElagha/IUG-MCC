package com.example.mohammed_elagha.simplechatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mohammed_elagha.simplechatapp.auth.AuthenticationSample;
import com.example.mohammed_elagha.simplechatapp.chat.ChatSample;
import com.example.mohammed_elagha.simplechatapp.chat.MessageReader;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView lastMagTV;
    ImageButton sendBtn;
    EditText editText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference chatsDatabaseReference;
    ChatSample chatSample;
    MessageReader messageReader;
    public static final String FIREBASE_DATABASE_CHILD_PATH = "chats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastMagTV = findViewById(R.id.textView);
        sendBtn = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        chatsDatabaseReference = databaseReference.child(FIREBASE_DATABASE_CHILD_PATH);

        chatSample = new ChatSample(chatsDatabaseReference);
        messageReader = new MessageReader();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageTxt = editText.getText().toString();
                chatSample.createMessage(messageTxt);
                lastMagTV.setText(messageReader.readMessage(chatsDatabaseReference));
            }
        });
    }

}
