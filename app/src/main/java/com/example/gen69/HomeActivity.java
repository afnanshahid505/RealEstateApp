package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnPlots, btnPgHostels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlots = findViewById(R.id.btnPlots);
        btnPgHostels = findViewById(R.id.btnPgHostels);

        // When Plots button is clicked
        btnPlots.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlotsActivity.class);
            startActivity(intent);
        });

        // When PG Hostels button is clicked
        btnPgHostels.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PgHostelsActivity.class);
            startActivity(intent);
        });
    }
}
