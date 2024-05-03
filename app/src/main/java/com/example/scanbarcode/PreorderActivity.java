package com.example.scanbarcode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreorderActivity extends AppCompatActivity {

    private DatabaseReference linggisRef;
    private TextView itemNameText, itemStockText;
    private Button requestButton;

    private int requestedQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder);

        linggisRef = FirebaseDatabase.getInstance().getReference().child("Linggis");
        itemNameText = findViewById(R.id.item_name_text);
        itemStockText = findViewById(R.id.item_stock_text);
        requestButton = findViewById(R.id.request_button);

        // Retrieve item information from Firebase
        linggisRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String itemName = dataSnapshot.child("itemName").getValue(String.class);
                    int itemStock = dataSnapshot.child("itemStock").getValue(Integer.class);
                    itemNameText.setText(itemName);
                    itemStockText.setText("Stok: " + itemStock);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
                Toast.makeText(PreorderActivity.this, "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preorderRequest();
            }
        });
    }

    private void preorderRequest() {
        linggisRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int itemStock = dataSnapshot.child("itemStock").getValue(Integer.class);
                    // Check if the requested quantity is greater than available stock
                    if (itemStock >= requestedQuantity) {
                        showPreorderPopup();
                    } else {
                        sendPreorderNotification();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
                Toast.makeText(PreorderActivity.this, "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Menggunakan setView() untuk menambahkan checkbox ke dalam dialog
    private void showPreorderPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Preorder");
        builder.setMessage("Barang telah tersedia untuk preorder. Silakan isi persyaratan berikut:");

        // Menambahkan layout XML khusus yang berisi checkbox dan teks persyaratan
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_preorder, null);
        builder.setView(dialogView);

        CheckBox agreementCheckBox = dialogView.findViewById(R.id.agreement_checkbox);
        // Menambahkan listener untuk menangani perubahan status checkbox
        agreementCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Logika penanganan perubahan status checkbox
            }
        });

        // Tombol preorder hanya akan aktif jika checkbox telah dicentang
        builder.setPositiveButton("Preorder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (agreementCheckBox.isChecked()) {
                    // Perform preorder action jika checkbox telah dicentang
                    Toast.makeText(PreorderActivity.this, "Preorder terkirim", Toast.LENGTH_SHORT).show();
                } else {
                    // Tampilkan pesan kesalahan jika checkbox belum dicentang
                    Toast.makeText(PreorderActivity.this, "Anda harus menyetujui persyaratan preorder terlebih dahulu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void sendPreorderNotification() {
        // Implement notification sending logic here
        Toast.makeText(PreorderActivity.this, "Stok tidak mencukupi untuk preorder", Toast.LENGTH_SHORT).show();
    }
}
