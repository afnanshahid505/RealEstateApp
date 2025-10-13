package com.example.gen69;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HouseFragment extends Fragment {

    RecyclerView recyclerView;
    PropertyAdapter adapter;
    List<PropertyModel> houseList;
    FirebaseFirestore db;
    String selectedLocation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHouses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        houseList = new ArrayList<>();
        adapter = new PropertyAdapter(getContext(), houseList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        if (selectedLocation != null) {
            fetchHouses();
        }

        return view;
    }

    public void updateLocation(String location) {
        this.selectedLocation = location;
        fetchHouses();
    }

    private void fetchHouses() {
        if (selectedLocation == null || selectedLocation.isEmpty()) {
            return;
        }

        db.collection("properties")
                .whereEqualTo("type", "House")
                .whereEqualTo("location", selectedLocation)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    houseList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        PropertyModel property = doc.toObject(PropertyModel.class);
                        houseList.add(property);
                    }
                    adapter.notifyDataSetChanged();

                    if (houseList.isEmpty()) {
                        Toast.makeText(getContext(),
                                "No houses found in " + selectedLocation,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(),
                                "Failed to fetch houses.", Toast.LENGTH_SHORT).show());
    }
}
