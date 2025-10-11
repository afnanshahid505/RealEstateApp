package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TenantDashboardActivity extends AppCompatActivity {

    Button btnViewHostels, btnWishlist, btnContactOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenantdashboard);

        btnViewHostels = findViewById(R.id.btnViewHostels);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnContactOwner = findViewById(R.id.btnContactOwner);

        btnViewHostels.setOnClickListener(v -> {
            Toast.makeText(this, "Fetching hostel listings...", Toast.LENGTH_SHORT).show();
            // Later: startActivity(new Intent(this, ViewHostelsActivity.class));
        });

        btnWishlist.setOnClickListener(v -> {
            startActivity(new Intent(this, WishlistActivity.class));
        });

        btnContactOwner.setOnClickListener(v -> {
            Toast.makeText(this, "Opening contact options...", Toast.LENGTH_SHORT).show();
        });
    }
}
