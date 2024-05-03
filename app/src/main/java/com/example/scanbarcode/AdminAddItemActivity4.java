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

public class AdminAddItemActivity4 extends AppCompatActivity {
    private EditText amountEditText4;
    private Button addButton4;

    private EditText amountEditTextPrice4;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item4);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        amountEditText4 = findViewById(R.id.amountEditText4);
        amountEditTextPrice4 = findViewById(R.id.amountEditTextPrice4);
        addButton4 = findViewById(R.id.addButton4);

        addButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountEditText4.getText().toString();
                String amountStringPrice = amountEditTextPrice4.getText().toString();
                // Mengonversi string ke tipe data numerik (misalnya, Integer)
                if (!amountString.isEmpty()&&!amountStringPrice.isEmpty()) {
                    int amount = Integer.parseInt(amountString); // Mengonversi string ke int
                    int amountPrice = Integer.parseInt(amountStringPrice); // Mengonversi string ke int
                    String itemBarcode = "1234567890";
                    // Create a Semen object
                    Semen semen = new Semen("Semen", itemBarcode,amount, amountPrice); // Sesuaikan "Nama Barang" dengan nama barang yang sesuai
                    // Menyimpan objek Semen ke Firebase Database
                    databaseReference.child("semen").setValue(semen)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Barang Sudah Ditambahkan", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AdminAddItemActivity4.this, AdminPurchaseActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Gagal menambahkan data barang: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Data Produk Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

