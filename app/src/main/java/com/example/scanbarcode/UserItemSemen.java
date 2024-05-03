package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserItemSemen extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button barcodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_semen);

        barcodeButton = findViewById(R.id.barcodeButton);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("semen");

        // Retrieve data from Firebase
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Semen semen = dataSnapshot.getValue(Semen.class);

                    // Display data in the layout
                    displaySemenData(semen);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserItemSemen.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserItemSemen.this, BarcodeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Function to display Semen data in the layout
    private void displaySemenData(Semen semen) {
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView itemCountTextView = findViewById(R.id.itemCountTextView);
        TextView itemCountPriceTextView = findViewById(R.id.itemCountPriceTextView);

        itemNameTextView.setText("Item Name: " + semen.getItemName());
        itemCountTextView.setText("Item Count: " + semen.getItemCount());
        itemCountPriceTextView.setText("Item Price: " + semen.getItemCountPrice());
    }
}
