package com.example.mohammed_elagha.firebasetestlabapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SuccessTestCase();
        // FailTestCase();
    }

    private void SuccessTestCase () {
        String txt = "This is a sample";
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    private void FailTestCase () {
        String txt = null;
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }
}
