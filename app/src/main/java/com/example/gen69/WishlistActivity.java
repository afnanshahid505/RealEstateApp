package com.example.gen69;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    ListView listViewWishlist;
    Button btnAddToCart, btnClearWishlist;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    List<String> wishlistItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        listViewWishlist = findViewById(R.id.listViewWishlist);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnClearWishlist = findViewById(R.id.btnClearWishlist);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        wishlistItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wishlistItems);
        listViewWishlist.setAdapter(adapter);

        if (currentUser == null) {
            Toast.makeText(this, "Please log in to view wishlist", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        fetchWishlist();

        btnAddToCart.setOnClickListener(v -> {
            // TODO: Add logic to move wishlist items to cart collection
            Toast.makeText(this, "Added all items to cart", Toast.LENGTH_SHORT).show();
        });

        btnClearWishlist.setOnClickListener(v -> {
            db.collection("wishlist")
                    .document(currentUser.getUid())
                    .collection("properties")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            doc.getReference().delete();
                        }
                        wishlistItems.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Wishlist cleared", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void fetchWishlist() {
        db.collection("wishlist")
                .document(currentUser.getUid())
                .collection("properties")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    wishlistItems.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String desc = doc.getString("description");
                        String loc = doc.getString("location");
                        wishlistItems.add(desc + " (" + loc + ")");
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to fetch wishlist", Toast.LENGTH_SHORT).show());
    }
}
