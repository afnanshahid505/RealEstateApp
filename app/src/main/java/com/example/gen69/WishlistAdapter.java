package com.example.gen69;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class WishlistAdapter extends BaseAdapter {

    public interface OnMapClickListener {
        void onMapClick(PropertyModel property);
    }

    public interface OnRemoveClickListener {
        void onRemoveClick(PropertyModel property);
    }

    private Context context;
    private List<PropertyModel> wishlist;
    private OnMapClickListener mapClickListener;
    private OnRemoveClickListener removeClickListener;

    public WishlistAdapter(Context context, List<PropertyModel> wishlist,
                           OnMapClickListener mapClickListener,
                           OnRemoveClickListener removeClickListener) {
        this.context = context;
        this.wishlist = wishlist;
        this.mapClickListener = mapClickListener;
        this.removeClickListener = removeClickListener;
    }

    @Override
    public int getCount() {
        return wishlist.size();
    }

    @Override
    public Object getItem(int position) {
        return wishlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_wishlist, parent, false);
        }

        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        TextView tvLocation = convertView.findViewById(R.id.tvLocation);
        Button btnRemove = convertView.findViewById(R.id.btnRemove);

        PropertyModel property = wishlist.get(position);
        tvDescription.setText(property.getDescription());
        tvLocation.setText(property.getLocation());

        // Click on the property to open Google Maps
        convertView.setOnClickListener(v -> {
            if (mapClickListener != null) {
                mapClickListener.onMapClick(property);
            } else {
                openMaps(property.getLocation());
            }
        });

        // Remove button click
        btnRemove.setOnClickListener(v -> {
            if (removeClickListener != null) {
                removeClickListener.onRemoveClick(property);
            }
        });

        return convertView;
    }

    private void openMaps(String location) {
        if (location == null || location.isEmpty()) return;
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            Toast.makeText(context, "Google Maps not found", Toast.LENGTH_SHORT).show();
        }
    }
}
