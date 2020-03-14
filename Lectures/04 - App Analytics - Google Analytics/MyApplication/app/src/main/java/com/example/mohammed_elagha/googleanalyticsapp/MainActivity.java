package com.example.mohammed_elagha.googleanalyticsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackScreen();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // when user select a content, call this function
        startLoggingEvent();

        // when user share an image, call this function
        String imageName = "anything.jpg";
        String shareTo = "WhatsApp";
        logginShareImage(imageName, shareTo);
    }

    // Logging event, use recommended event
    private void startLoggingEvent () {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "22");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "ITEM");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    // Logging event, use custom event
    private void logginShareImage (String imageName, String shareTo) {
        Bundle bundle = new Bundle();
        bundle.putString("image_name", imageName);
        bundle.putString("share_to",shareTo);
        mFirebaseAnalytics.logEvent("share_image", bundle);
    }


    // set some user property
    private void setUserProperty () {
        mFirebaseAnalytics.setUserProperty("offer-clothes", "high");
    }


    // set screen track
    private void trackScreen () {
        mFirebaseAnalytics.setCurrentScreen(this, "home_ui", null);
    }
}
