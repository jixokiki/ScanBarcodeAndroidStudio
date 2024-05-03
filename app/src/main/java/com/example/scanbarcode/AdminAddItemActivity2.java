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

public class AdminAddItemActivity2 extends AppCompatActivity {

    private EditText amountEditText2;
    private Button addButton2;
    private EditText amountPriceEditText2;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item2);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        amountEditText2 = findViewById(R.id.amountEditText2);
        amountPriceEditText2 = findViewById(R.id.amountPriceEditText2);
        addButton2 = findViewById(R.id.addButton2);

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountEditText2.getText().toString();
                String amountPriceString = amountPriceEditText2.getText().toString();
                // Mengonversi string ke tipe data numerik (misalnya, Integer)
                if (!amountString.isEmpty()&&!amountPriceString.isEmpty()) {
                    int amount = Integer.parseInt(amountString); // Mengonversi string ke int
                    int amountPrice = Integer.parseInt(amountPriceString); // Mengonversi string ke int
                    // Create a Semen object
                    Paku paku = new Paku("paku", amount, amountPrice); // Sesuaikan "Nama Barang" dengan nama barang yang sesuai
                    // Menyimpan objek Semen ke Firebase Database
                    databaseReference.child("paku").setValue(paku)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Barang Sudah Ditambahkan", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AdminAddItemActivity2.this, AdminPurchaseActivity.class);
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
