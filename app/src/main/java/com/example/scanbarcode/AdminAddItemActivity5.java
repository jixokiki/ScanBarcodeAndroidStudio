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

public class AdminAddItemActivity5 extends AppCompatActivity {

    private EditText amountEditText5;
    private Button addButton5;
    private EditText amountPriceEditText5;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item5);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        amountEditText5 = findViewById(R.id.amountEditText5);
        amountPriceEditText5 = findViewById(R.id.amountPriceEditText5);
        addButton5 = findViewById(R.id.addButton5);

        addButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountEditText5.getText().toString();
                String amountPriceString = amountPriceEditText5.getText().toString();
                // Mengonversi string ke tipe data numerik (misalnya, Integer)
                if (!amountString.isEmpty()&&!amountPriceString.isEmpty()) {
                    int amount = Integer.parseInt(amountString); // Mengonversi string ke int
                    int amountPrice = Integer.parseInt(amountPriceString); // Mengonversi string ke int
                    // Create a Semen object
                    Bata bata = new Bata("Bata", amount, amountPrice); // Sesuaikan "Nama Barang" dengan nama barang yang sesuai
                    // Menyimpan objek Semen ke Firebase Database
                    databaseReference.child("bata").setValue(bata)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Barang Sudah Ditambahkan", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AdminAddItemActivity5.this, AdminPurchaseActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Gagal menambahkan data barang: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });                } else {
                    Toast.makeText(getApplicationContext(), "Data Produk Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void addItemToFirebase(int amount) {
//        // Generate unique key for the item
//        String itemId = databaseReference.push().getKey();
//
//        // Create item object
//        Bata bata = new Bata(itemId, amount);
//
//        // Save item to Firebase
//        databaseReference.child(itemId).setValue(bata)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(AdminAddItemActivity5.this, "Item added successfully to Firebase", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AdminAddItemActivity5.this, "Failed to add item to Firebase: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}
