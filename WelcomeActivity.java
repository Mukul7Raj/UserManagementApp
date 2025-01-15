package com.example.usermanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = findViewById(R.id.welcomeText);
        logoutButton = findViewById(R.id.logoutButton);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String username = getIntent().getStringExtra("username");
        welcomeText.setText("Welcome, " + username + "!");

        logoutButton.setOnClickListener(view -> {
            sharedPreferences.edit().clear().apply();
            startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
            finish();
        });
    }
}
