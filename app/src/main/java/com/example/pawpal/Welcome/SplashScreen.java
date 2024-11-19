package com.example.pawpal.Welcome;

import static com.example.pawpal.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawpal.MainActivity;
import com.example.pawpal.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.splashscreen); // Ensure this references your layout

        final ImageView logo = findViewById(R.id.pawpal_logo);
        final TextView pawpal = findViewById(id.pawpal);
        // Load animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.smart_animation);
        //hide pawpal text
        pawpal.setVisibility(TextView.GONE);
        logo.startAnimation(animation);
        // Delay for 2000ms (2 seconds) before navigating
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to the welcome activity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        }, 2000); // Delay of 2000ms
    }
}
