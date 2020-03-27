package com.example.mohammed_elagha.samplechatapp2;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.widgets.Snapshot;

public class MessageReader {
    private DatabaseReference chatsReference;

    public MessageReader(DatabaseReference chatsReference) {
        this.chatsReference = chatsReference;
    }

    public String readMessage () {
        String text = "";

        chatsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Message message = snapshot.getValue(Message.class);
                    Log.d("LAST_MESSAGE", message.getText());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return text;
    }
}
