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

public class AdminAddItemActivity3 extends AppCompatActivity {

    private EditText amountEditText3;

    private EditText amountPriceEditText3;
    private Button addButton3;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item3);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://scanbarcode-5165c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        amountEditText3 = findViewById(R.id.amountEditText3);
        amountPriceEditText3 = findViewById(R.id.amountPriceEditText3);
        addButton3 = findViewById(R.id.addButton3);

        addButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountEditText3.getText().toString();
                String amountPriceString = amountPriceEditText3.getText().toString();
                // Mengonversi string ke tipe data numerik (misalnya, Integer)
                if (!amountString.isEmpty()&&!amountPriceString.isEmpty()) {
                    int amount = Integer.parseInt(amountString); // Mengonversi string ke int
                    int amountPrice = Integer.parseInt(amountPriceString); // Mengonversi string ke int
                    // Create a Semen object
                    Linggis linggis = new Linggis("Linggis", amount, amountPrice); // Sesuaikan "Nama Barang" dengan nama barang yang sesuai
                    // Menyimpan objek Semen ke Firebase Database
                    databaseReference.child("linggis").setValue(linggis)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Data Barang Sudah Ditambahkan", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AdminAddItemActivity3.this, AdminPurchaseActivity.class);
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
