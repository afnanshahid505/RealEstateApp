package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlotsActivity extends AppCompatActivity {

    Button btnSeller, btnBuyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plots);

        btnSeller = findViewById(R.id.btnSeller);
        btnBuyer = findViewById(R.id.btnBuyer);

        // When Seller button is clicked
        btnSeller.setOnClickListener(v -> {
            Intent intent = new Intent(PlotsActivity.this, SellerDashboardActivity.class);
            startActivity(intent);
        });

        // When Buyer button is clicked
        btnBuyer.setOnClickListener(v -> {
            Intent intent = new Intent(PlotsActivity.this, BuyerDashboardActivity.class);
            startActivity(intent);
        });
    }
}
