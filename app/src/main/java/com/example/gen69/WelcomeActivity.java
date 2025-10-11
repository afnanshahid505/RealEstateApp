package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView genText = findViewById(R.id.genText);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        genText.startAnimation(fadeOut);

        // Delay 2 seconds, then go to HomePageActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}
