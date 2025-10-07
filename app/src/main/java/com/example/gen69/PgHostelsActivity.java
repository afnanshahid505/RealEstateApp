package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PgHostelsActivity extends AppCompatActivity {

    Button btnOwner, btnTenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pghostels);

        btnOwner = findViewById(R.id.btnOwner);
        btnTenant = findViewById(R.id.btnTenant);

        // Navigate to Owner Dashboard
        btnOwner.setOnClickListener(v -> {
            Intent intent = new Intent(PgHostelsActivity.this, OwnerDashboardActivity.class);
            startActivity(intent);
        });

        // Navigate to Tenant Dashboard
        btnTenant.setOnClickListener(v -> {
            Intent intent = new Intent(PgHostelsActivity.this, TenantDashboardActivity.class);
            startActivity(intent);
        });
    }
}
