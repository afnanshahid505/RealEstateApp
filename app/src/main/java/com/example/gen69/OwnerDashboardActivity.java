package com.example.gen69;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerDashboardActivity extends AppCompatActivity {

    EditText etHostelName, etLocation, etSharing, etFacilities, etRent, etDescription;
    Button btnSubmitHostel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerdashboard);

        etHostelName = findViewById(R.id.etHostelName);
        etLocation = findViewById(R.id.etLocation);
        etSharing = findViewById(R.id.etSharing);
        etFacilities = findViewById(R.id.etFacilities);
        etRent = findViewById(R.id.etRent);
        etDescription = findViewById(R.id.etDescription);
        btnSubmitHostel = findViewById(R.id.btnSubmitHostel);

        btnSubmitHostel.setOnClickListener(v -> {
            String name = etHostelName.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String sharing = etSharing.getText().toString().trim();
            String facilities = etFacilities.getText().toString().trim();
            String rent = etRent.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();

            if (name.isEmpty() || location.isEmpty() || rent.isEmpty()) {
                Toast.makeText(this, "Please fill all mandatory fields!", Toast.LENGTH_SHORT).show();
            } else {
                // ✅ Save details to Firebase / SQLite (future)
                Toast.makeText(this, "Hostel details submitted successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
