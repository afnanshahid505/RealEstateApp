package com.example.gen69;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class Splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TextView genText = findViewById(R.id.genText);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        genText.startAnimation(zoomOut);

        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.getString("email", "");

            Intent intent;
            intent = new Intent(Splashscreen.this, MainActivity.class);

            startActivity(intent);
            finish();
        }, 5000); // 5-second delay
    }
}
