package com.example.gen69;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private ListView listViewWishlist;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private List<PropertyModel> wishlistItems;
    private WishlistAdapter adapter;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        listViewWishlist = findViewById(R.id.listViewWishlist);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentUserId = auth.getCurrentUser().getUid();
        wishlistItems = new ArrayList<>();

        adapter = new WishlistAdapter(this, wishlistItems,
                property -> openInMaps(property.getLocation()), // click to open map
                property -> removeFromWishlist(property));      // click remove
        listViewWishlist.setAdapter(adapter);

        loadWishlist();
    }

    private void loadWishlist() {
        db.collection("wishlist")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    wishlistItems.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        PropertyModel property = doc.toObject(PropertyModel.class);
                        property.setId(doc.getId()); // store Firestore doc ID for remove
                        wishlistItems.add(property);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to load wishlist", Toast.LENGTH_SHORT).show());
    }

    private void removeFromWishlist(PropertyModel property) {
        if (property.getId() == null) return;

        db.collection("wishlist").document(property.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    wishlistItems.remove(property);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to remove", Toast.LENGTH_SHORT).show());
    }

    private void openInMaps(String location) {
        if (location == null || location.isEmpty()) return;

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google Maps not found", Toast.LENGTH_SHORT).show();
        }
    }
}
