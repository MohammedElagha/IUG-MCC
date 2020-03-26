package com.example.mohammed_elagha.simplechatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mohammed_elagha.simplechatapp.auth.AuthenticationSample;
import com.example.mohammed_elagha.simplechatapp.chat.ChatSample;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView lastMagTV;
    ImageButton sendBtn;
    EditText editText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ChatSample chatSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastMagTV = findViewById(R.id.textView);
        sendBtn = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        chatSample = new ChatSample(databaseReference);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageTxt = editText.getText().toString();
                lastMagTV.setText(messageTxt);
                chatSample.createMessage(messageTxt);
            }
        });
    }

}
