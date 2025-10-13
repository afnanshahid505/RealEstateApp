package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BuyerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etLocation;
    private TextView tvUserLocation;
    private Button btnSearchLocation;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private BuyerPagerAdapter adapter;
    private String lastLocation = null;

    // Store fragments for direct access
    private PlotsFragment plotsFragment;
    private HouseFragment houseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);

        toolbar = findViewById(R.id.toolbar);
        etLocation = findViewById(R.id.etLocation);
        tvUserLocation = findViewById(R.id.tvUserLocation);
        btnSearchLocation = findViewById(R.id.btnSearchLocation);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Buyer");
        }

        // Initialize fragments
        plotsFragment = new PlotsFragment();
        houseFragment = new HouseFragment();

        adapter = new BuyerPagerAdapter(this, plotsFragment, houseFragment);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(position == 0 ? "Plots" : "Houses");
        }).attach();

        btnSearchLocation.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        String location = etLocation.getText().toString().trim();

        if (location.isEmpty()) {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return;
        }

        lastLocation = location;
        tvUserLocation.setText(location);

        // wait for fragments to attach properly before updating
        new Handler(Looper.getMainLooper()).postDelayed(this::updateFragments, 400);
    }

    private void updateFragments() {
        if (lastLocation == null) return;

        try {
            if (plotsFragment.isAdded()) {
                plotsFragment.updateLocation(lastLocation);
            }

            if (houseFragment.isAdded()) {
                houseFragment.updateLocation(lastLocation);
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error updating fragments: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Add cart (wishlist) button to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem cartItem = menu.add("Wishlist");
        cartItem.setIcon(android.R.drawable.ic_menu_agenda); // or your cart drawable
        cartItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        cartItem.setOnMenuItemClickListener(item -> {
            // Open WishlistActivity
            Intent intent = new Intent(BuyerActivity.this, WishlistActivity.class);
            startActivity(intent);
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
