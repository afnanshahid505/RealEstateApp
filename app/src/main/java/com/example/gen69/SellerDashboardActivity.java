package com.example.gen69;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SellerDashboardActivity extends AppCompatActivity {

    EditText etLocation, etPrice, etArea, etDescription;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerdashboard);

        etLocation = findViewById(R.id.etLocation);
        etPrice = findViewById(R.id.etPrice);
        etArea = findViewById(R.id.etArea);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String loc = etLocation.getText().toString();
            String price = etPrice.getText().toString();
            String area = etArea.getText().toString();
            String desc = etDescription.getText().toString();

            if (loc.isEmpty() || price.isEmpty() || area.isEmpty()) {
                Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            } else {
                // Here you can store data in Firebase or SQLite
                Toast.makeText(this, "Plot details submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
