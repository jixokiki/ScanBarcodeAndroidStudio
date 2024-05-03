package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeActivity extends AppCompatActivity {

    private Button scanButton;
    private DatabaseReference mDatabase;
    private String selectedItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        scanButton = findViewById(R.id.scanButton);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("semen");

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start barcode scanning
                IntentIntegrator integrator = new IntentIntegrator(BarcodeActivity.this);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("Scan a barcode");
                integrator.initiateScan();
            }
        });
    }

    // Handle result of barcode scanning
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // Handle barcode scan result
                String scannedBarcode = result.getContents();
                // Implement logic to reduce item count based on the scanned barcode
                reduceItemCount(scannedBarcode);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // Function to reduce item count based on scanned barcode
    private void reduceItemCount(final String scannedBarcode) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Semen semen = snapshot.getValue(Semen.class);
                    if (semen != null && semen.getItemBarcode().equals(scannedBarcode)) {
                        int currentItemCount = semen.getItemCount();
                        // Implement logic to reduce item count by 1
                        mDatabase.child(snapshot.getKey()).child("itemCount").setValue(currentItemCount - 1);
                        Toast.makeText(BarcodeActivity.this, "Item count reduced", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(BarcodeActivity.this, "Item not found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BarcodeActivity.this, "Failed to update item count: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


