package com.example.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText passwordEditText;
    private Button loginButton;
    private final String adminPassword = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPassword = passwordEditText.getText().toString();
                if (enteredPassword.equals(adminPassword)) {
                    // Admin login successful, navigate to AdminAddItemActivity
                    Toast.makeText(getApplicationContext(), "Admin login successful", Toast.LENGTH_SHORT).show();
                    // Intent to navigate to AdminAddItemActivity
                    Intent intent = new Intent(AdminLoginActivity.this, AdminPurchaseActivity.class);
                    startActivity(intent);
//                    finish(); // Optional: finish current activity
                } else {
                    // Incorrect password
                    Toast.makeText(AdminLoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
