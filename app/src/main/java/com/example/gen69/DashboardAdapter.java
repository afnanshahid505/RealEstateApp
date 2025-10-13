package com.example.gen69;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private Context context;
    private List<PropertyModel> propertyList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DashboardAdapter(Context context, List<PropertyModel> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_property, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PropertyModel property = propertyList.get(position);

        holder.tvDescription.setText(property.getDescription() != null ? property.getDescription() : "No Description");
        holder.tvLocation.setText(property.getLocation() != null ? property.getLocation() : "Unknown Location");
        holder.tvType.setText(property.getType() != null ? property.getType() : "Unknown Type");

        holder.btnDelete.setOnClickListener(v -> {
            if (property.getId() != null) {
                db.collection("properties").document(property.getId())
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            propertyList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, propertyList.size());
                            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(context, "Property ID missing", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription, tvLocation, tvType;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvType = itemView.findViewById(R.id.tvType);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
