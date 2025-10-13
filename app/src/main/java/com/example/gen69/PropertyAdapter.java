package com.example.gen69;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private Context context;
    private List<PropertyModel> propertyList;

    public PropertyAdapter(Context context, List<PropertyModel> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        PropertyModel property = propertyList.get(position);

        holder.tvDescription.setText(property.getDescription() != null ? property.getDescription() : "No Description");
        holder.tvLocation.setText(property.getLocation() != null ? property.getLocation() : "Unknown Location");

        // No image field in DB → use static placeholder
        holder.ivProperty.setImageResource(R.drawable.bg2);

        // Click property → open Google Maps
        holder.itemView.setOnClickListener(v -> {
            if (property.getLocation() != null && !property.getLocation().isEmpty()) {
                try {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(property.getLocation()));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Google Maps not available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Location not available", Toast.LENGTH_SHORT).show();
            }
        });

        // Add to Wishlist button functionality
        holder.btnAddToWishlist.setOnClickListener(v -> {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, Object> wishlistItem = new HashMap<>();
            wishlistItem.put("userId", userId);
            wishlistItem.put("description", property.getDescription());
            wishlistItem.put("location", property.getLocation());
            wishlistItem.put("type", property.getType());

            db.collection("wishlist")
                    .add(wishlistItem)
                    .addOnSuccessListener(aVoid ->
                            Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(context, "Failed to add to wishlist", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProperty;
        TextView tvDescription, tvLocation;
        Button btnAddToWishlist;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProperty = itemView.findViewById(R.id.ivProperty);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            btnAddToWishlist = itemView.findViewById(R.id.btnAddToWishlist); // Add this in your item_property.xml
        }
    }
}
