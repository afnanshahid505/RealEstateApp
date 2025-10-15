package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button btnSell, btnBuy;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Enable the ☰ icon
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // Display user's email as the title (or "Home" if not available)
        if (currentUser != null && currentUser.getEmail() != null) {
            getSupportActionBar().setTitle("Welcome, " + currentUser.getEmail());
        } else {
            getSupportActionBar().setTitle("Home");
        }

        // Buttons
        btnSell = findViewById(R.id.btnSell);
        btnBuy = findViewById(R.id.btnBuy);

        btnSell.setOnClickListener(v -> startActivity(new Intent(this, SellerActivity.class)));
        btnBuy.setOnClickListener(v -> startActivity(new Intent(this, BuyerActivity.class)));
    }

    // Inflate the logout button on the right
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem logoutItem = menu.add("Logout");
        logoutItem.setIcon(R.drawable.ic_logout);
        logoutItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // ☰ icon clicked
        if (id == android.R.id.home) {
            View menuIconView = findViewById(android.R.id.home);
            if (menuIconView == null) menuIconView = toolbar;

            PopupMenu popupMenu = new PopupMenu(this, menuIconView);
            popupMenu.getMenuInflater().inflate(R.menu.home_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int menuId = menuItem.getItemId();
                 if (menuId == R.id.menu_dashboard) {
                    Intent sw= new Intent(HomeActivity.this, DashboardActivity.class);
                    startActivity(sw);
                } else if (menuId == R.id.menu_wishlist) {
                    Intent sd= new Intent (HomeActivity.this,WishlistActivity.class);
                    startActivity(sd);
                }
                return true;
            });
            popupMenu.show();
            return true;
        }

        // Logout button clicked
        if (item.getTitle() != null && item.getTitle().toString().equals("Logout")) {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();

            // Sign out from Firebase
            mAuth.signOut();

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
