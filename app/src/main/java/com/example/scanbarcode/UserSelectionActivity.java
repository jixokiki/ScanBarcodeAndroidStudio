package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserSelectionActivity extends AppCompatActivity {
    private Button selectItemButton;

    private  Button preorderItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        selectItemButton = findViewById(R.id.selectItemButton);
        preorderItemButton = findViewById(R.id.preorderItemButton);

        selectItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSelectionActivity.this, UserItem.class);
                startActivity(intent);
            }
        });

        preorderItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSelectionActivity.this, PreorderActivity.class);
                startActivity(intent);
            }
        });
    }
}

