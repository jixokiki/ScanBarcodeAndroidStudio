package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserItem extends AppCompatActivity {
    private Button pakuButton;
    private Button linggisButton;
    private Button semenButton;
    private Button bataButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_purchase);

        pakuButton = findViewById(R.id.pakuButton);
        linggisButton = findViewById(R.id.linggisButton);
        semenButton = findViewById(R.id.semenButton);
        bataButton = findViewById(R.id.bataButton);

        pakuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserItem.this, UserItemPaku.class);
                startActivity(intent);
            }
        });

        linggisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserItem.this, UserItemLinggis.class);
                startActivity(intent);
            }
        });

        semenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserItem.this, UserItemSemen.class);
                startActivity(intent);
            }
        });

        bataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserItem.this, AdminAddItemActivity5.class);
                startActivity(intent);
            }
        });


    }
}
