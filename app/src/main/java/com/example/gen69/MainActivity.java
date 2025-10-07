package com.example.gen69;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;  // You need to get user input
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView goToSignupText; // "Don't have an account?" TextView
    EditText emailEditText, passwordEditText; // Input fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.loginButton);
        goToSignupText = findViewById(R.id.goToSignupText);
        emailEditText = findViewById(R.id.emailEditText);  // Add these EditTexts in your activity_main.xml if not already
        passwordEditText = findViewById(R.id.passwordEditText);

        b1.setOnClickListener(view -> {
            String inputEmail = emailEditText.getText().toString().trim();
            String inputPassword = passwordEditText.getText().toString().trim();

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedEmail = prefs.getString("email", "");
            String savedPassword = prefs.getString("password", "");

            if (inputEmail.equals(savedEmail)) {
                if (inputPassword.equals(savedPassword)) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();
            }
        });

        goToSignupText.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
            // No finish() here so user can come back with back button
        });
    }

    public static class Hostel {
        private String name, location, rent, imageUrl;

        public Hostel() {}

        public Hostel(String name, String location, String rent, String imageUrl) {
            this.name = name;
            this.location = location;
            this.rent = rent;
            this.imageUrl = imageUrl;
        }

        public String getName() { return name; }
        public String getLocation() { return location; }
        public String getRent() { return rent; }
        public String getImageUrl() { return imageUrl; }
    }
}
