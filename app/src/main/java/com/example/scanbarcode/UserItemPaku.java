package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserItemPaku extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_paku);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("paku");

        // Retrieve data from Firebase
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Paku paku = dataSnapshot.getValue(Paku.class);

                    // Display data in the layout
                    displaySemenData(paku);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserItemPaku.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Function to display Semen data in the layout
    private void displaySemenData(Paku paku) {
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView itemCountTextView = findViewById(R.id.itemCountTextView);
        TextView itemPriceTextView = findViewById(R.id.itemPriceTextView);

        itemNameTextView.setText("Item Name: " + paku.getItemName());
        itemCountTextView.setText("Item Count: " + paku.getItemCount());
        itemPriceTextView.setText("Item Price: " + paku.getItemPrice());
    }

    // Method to handle scan barcode button click
    public void scanBarcode(View view) {
        // Intent to switch to the barcode layout activity
        Intent intent = new Intent(UserItemPaku.this, BarcodeActivityPaku.class);
        startActivity(intent);
    }
}

