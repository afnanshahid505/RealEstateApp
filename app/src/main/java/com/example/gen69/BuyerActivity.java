package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BuyerActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etLocation;
    TextView tvUserLocation;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    Button btnSearch; // search button below location input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back arrow
            getSupportActionBar().setTitle("Buyer");
        }

        etLocation = findViewById(R.id.etLocation);
        tvUserLocation = findViewById(R.id.tvUserLocation);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnSearch = findViewById(R.id.btnSearchLocation); // the search button below location input

        // Adapter for ViewPager2
        BuyerPagerAdapter adapter = new BuyerPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) tab.setText("Plots");
                    else tab.setText("Houses/Rent");
                }).attach();

        // Search button functionality
        btnSearch.setOnClickListener(v -> {
            String loc = etLocation.getText().toString().trim();
            if (!loc.isEmpty()) {
                tvUserLocation.setText(loc);

                // Pass location to both fragments
                PlotsFragment plotsFragment = (PlotsFragment) adapter.createFragment(0);
                plotsFragment.updateLocation(loc);

                HouseFragment houseFragment = (HouseFragment) adapter.createFragment(1);
                houseFragment.updateLocation(loc);

            } else {
                Toast.makeText(this, "Enter a location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Add Wishlist button in toolbar
        android.view.MenuItem wishlistItem = menu.add("Wishlist");
        wishlistItem.setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_ALWAYS);
        wishlistItem.setIcon(R.drawable.ic_wishlist); // make sure you have this drawable
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getTitle() != null && item.getTitle().toString().equals("Wishlist")) {
            // Open Wishlist activity
            Intent intent = new Intent(this, WishlistActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // back to previous activity
        return true;
    }
}
