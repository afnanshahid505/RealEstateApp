package com.example.gen69;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listViewCart;
    Button btnCheckout;
    ArrayList<String> cartItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listViewCart = findViewById(R.id.listViewCart);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartItems = getIntent().getStringArrayListExtra("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        listViewCart.setAdapter(adapter);

        btnCheckout.setOnClickListener(v ->
                Toast.makeText(this, "Proceeding to checkout / contact...", Toast.LENGTH_SHORT).show());
    }
}
