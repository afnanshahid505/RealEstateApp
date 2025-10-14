package com.example.gen69;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView userName, userEmail;
    ImageView profileImage;
    Button backButton;
    LinearLayout menuSettings, menuOrders;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        profileImage = findViewById(R.id.profile_image);
        backButton = findViewById(R.id.button); // Back button
        menuSettings = findViewById(R.id.menu_settings);
        menuOrders = findViewById(R.id.menu_orders);

        user = mAuth.getCurrentUser();

        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUrl = user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null;

            userName.setText(name != null ? name : "No name set");
            userEmail.setText(email != null ? email : "No email");

            if (photoUrl != null) {
                Glide.with(this)
                        .load(photoUrl)
                        .into(profileImage);
            } else {
                profileImage.setImageResource(R.drawable.ic_user_placeholder);
            }
        }

        // Settings Click
        menuSettings.setOnClickListener(v -> {
            Toast.makeText(ProfileActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
        });

        // My Orders Click
        menuOrders.setOnClickListener(v -> {
            Toast.makeText(ProfileActivity.this, "My Orders clicked", Toast.LENGTH_SHORT).show();
        });

        // Back Button Click
        backButton.setOnClickListener(v -> {
            finish(); // Close ProfileActivity
        });
    }
}
