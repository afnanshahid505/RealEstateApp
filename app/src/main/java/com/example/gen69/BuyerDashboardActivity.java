package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BuyerDashboardActivity extends AppCompatActivity {

    Button btnViewPlots, btnWishlist, btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerdashboard);

        btnViewPlots = findViewById(R.id.btnViewPlots);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnContact = findViewById(R.id.btnContact);

        btnViewPlots.setOnClickListener(v -> {
            // Show available plots
            Toast.makeText(this, "Showing available plots...", Toast.LENGTH_SHORT).show();
        });

        btnWishlist.setOnClickListener(v -> {
            startActivity(new Intent(this, WishlistActivity.class));
        });

        btnContact.setOnClickListener(v -> {
            // Contact seller logic
            Toast.makeText(this, "Contacting seller...", Toast.LENGTH_SHORT).show();
        });
    }
}
