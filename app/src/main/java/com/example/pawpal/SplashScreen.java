package com.example.pawpal;

import static com.example.pawpal.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this references your layout

        final ImageView logo = findViewById(R.id.pawpal_logo);

        // Load animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.smart_animation);
        logo.startAnimation(animation);

        // Delay for 2000ms (2 seconds) before navigating
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to the welcome activity
                Intent intent = new Intent(SplashScreen.this, welcome.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        }, 2000); // Delay of 2000ms
    }
}
