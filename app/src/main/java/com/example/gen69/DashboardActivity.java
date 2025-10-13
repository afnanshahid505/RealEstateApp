package com.example.gen69;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<PropertyModel> propertyList = new ArrayList<>();
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("My Dashboard");

        recyclerView = findViewById(R.id.recyclerViewDashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DashboardAdapter(this, propertyList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) loadProperties();
        else Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
    }

    private void loadProperties() {
        db.collection("properties")
                .whereEqualTo("userId", currentUser.getUid())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    propertyList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        PropertyModel property = doc.toObject(PropertyModel.class);
                        property.setId(doc.getId()); // essential
                        propertyList.add(property);
                    }
                    adapter.notifyDataSetChanged();

                    if (propertyList.isEmpty()) {
                        Toast.makeText(this, "No properties listed yet", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to load properties", Toast.LENGTH_SHORT).show());
    }
}
