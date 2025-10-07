package com.example.gen69;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    ListView listView;
    Button btnAddToCart, btnClearWishlist;
    ArrayList<String> wishlistItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        listView = findViewById(R.id.listViewWishlist);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnClearWishlist = findViewById(R.id.btnClearWishlist);

        wishlistItems = new ArrayList<>();
        wishlistItems.add("Plot near City Center - ₹20 L");
        wishlistItems.add("Hostel A – 2 Sharing – ₹6 000/mo");
        wishlistItems.add("Plot in Green Valley - ₹15 L");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wishlistItems);
        listView.setAdapter(adapter);

        btnAddToCart.setOnClickListener(v -> {
            Intent i = new Intent(this, CartActivity.class);
            i.putStringArrayListExtra("cartItems", wishlistItems);
            startActivity(i);
        });

        btnClearWishlist.setOnClickListener(v -> {
            wishlistItems.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Wishlist cleared!", Toast.LENGTH_SHORT).show();
        });
    }
}
