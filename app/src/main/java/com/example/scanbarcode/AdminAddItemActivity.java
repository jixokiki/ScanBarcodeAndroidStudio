package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddItemActivity extends AppCompatActivity {
    private EditText amountEditText;
    private Button addButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

//        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/").child("items");
//        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        amountEditText = findViewById(R.id.amountEditText);
        addButton = findViewById(R.id.addButton);

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String amountString = amountEditText.getText().toString().trim();
//                if (!amountString.isEmpty()) {
//                    int amount = Integer.parseInt(amountString);
//                    addItemToFirebase(amount);
//                } else {
//                    Toast.makeText(AdminAddItemActivity.this, "Please enter the amount", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String amountString = amountEditText.getText().toString();
//                if (!amountString.isEmpty()) {
//                    Toast.makeText(getApplicationContext(),"Data Produk Kosong", Toast.LENGTH_SHORT).show();
//                } else {
//                    databaseReference = FirebaseDatabase.getInstance().getReference("items");
//                    databaseReference.child(amountString).child("jumlah barang").setValue(amountString);
//
//                    Toast.makeText(getApplicationContext(),"Data Barang Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent( AdminAddItemActivity.this, AdminLoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountEditText.getText().toString();
                // Mengonversi string ke tipe data numerik (misalnya, Integer)
                if (!amountString.isEmpty()) {
                    int amount = Integer.parseInt(amountString); // Mengonversi string ke int
                    databaseReference = FirebaseDatabase.getInstance().getReference("items");
                    // Menyimpan nilai amount ke Firebase Database
                    databaseReference.child("jumlah_barang").setValue(amount)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Barang Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AdminAddItemActivity.this, AdminLoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Gagal menambahkan data barang: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(),"Data Produk Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addItemToFirebase(int amount) {
        // Generate unique key for the item
        String itemId = databaseReference.push().getKey();

        // Create item object
        Item item = new Item(itemId, amount);

        // Save item to Firebase
        databaseReference.child(itemId).setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AdminAddItemActivity.this, "Item added successfully to Firebase", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminAddItemActivity.this, "Failed to add item to Firebase: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

