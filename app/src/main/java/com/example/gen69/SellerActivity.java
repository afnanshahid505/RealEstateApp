package com.example.gen69;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SellerActivity extends AppCompatActivity {

    EditText descriptionEditText, locationEditText;
    Button btnSelectImage, btnSubmitProperty;
    ImageView imageView;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell);

        // Firebase setup
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        // Toolbar setup
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sell Property");
        }

        descriptionEditText = findViewById(R.id.etDescription);
        locationEditText = findViewById(R.id.etLocation);
        btnSelectImage = findViewById(R.id.btnSelectImage); // still visible, not used yet
        btnSubmitProperty = findViewById(R.id.btnSubmitProperty);
        imageView = findViewById(R.id.ivPropertyImage); // still in layout

        // Submit property
        btnSubmitProperty.setOnClickListener(v -> {
            String description = descriptionEditText.getText().toString().trim();
            String location = locationEditText.getText().toString().trim();

            if (description.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Choose property type
            String[] types = {"Plot", "House"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Property Type");
            builder.setItems(types, (dialog, which) -> {
                String propertyType = types[which];
                uploadProperty(description, location, propertyType);
            });
            builder.show();
        });
    }

    // Add Dashboard option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem dashboardItem = menu.add("Dashboard");
        dashboardItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() != null && item.getTitle().toString().equals("Dashboard")) {
            Toast.makeText(this, "User's Dashboard clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadProperty(String description, String location, String type) {
        if (currentUser == null) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> property = new HashMap<>();
        property.put("description", description);
        property.put("location", location);
        property.put("type", type);
        property.put("userId", currentUser.getUid());

        firestore.collection("properties")
                .add(property)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Property uploaded successfully!", Toast.LENGTH_LONG).show();
                    descriptionEditText.setText("");
                    locationEditText.setText("");
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Upload failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
