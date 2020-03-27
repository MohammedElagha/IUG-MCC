package com.example.mohammed_elagha.samplechatapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageButton sendBtn;
    EditText editText;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference parentReference;
    DatabaseReference chatsReference;
    MessageSender messageSender;
    MessageReader messageReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        parentReference = firebaseDatabase.getReference();
        chatsReference = parentReference.child(SampleConstants.DATABASE_CHATS_REFERENCE);

        messageSender = new MessageSender(chatsReference);
        messageReader = new MessageReader(chatsReference);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Message message = new Message(text, SampleConstants.SENDER_UID, SampleConstants.RECEIVER_UID);
                messageSender.sendMessage(message);

                messageReader.readMessage();
            }
        });
    }
}
