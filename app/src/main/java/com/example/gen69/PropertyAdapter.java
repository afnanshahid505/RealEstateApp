package com.example.gen69;

import android.content.Context;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

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

        holder.tvDescription.setText(property.getDescription());
        holder.tvLocation.setText(property.getLocation());

        Glide.with(context)
                .load(property.getImageUrl())
                .placeholder(R.drawable.bg2)
                .into(holder.ivProperty);

        // Wishlist button click
        holder.btnAddToWishlist.setOnClickListener(v -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                FirebaseFirestore.getInstance()
                        .collection("wishlist")
                        .document(currentUser.getUid())
                        .collection("properties")
                        .document() // auto-generated ID
                        .set(property)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(context, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(context, "Please log in to add to wishlist", Toast.LENGTH_SHORT).show();
            }
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
            btnAddToWishlist = itemView.findViewById(R.id.btnAddToWishlist); // new button
        }
    }
}
