package com.example.usermanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Spinner userTypeSpinner;
    private Button registerButton;
    private TextView loginLink;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        userTypeSpinner = findViewById(R.id.userType);
        registerButton = findViewById(R.id.registerButton);
        loginLink = findViewById(R.id.loginLink);
        auth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        registerButton.setOnClickListener(view -> registerUser());
        loginLink.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(username + "@example.com", password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "User " + username + " is registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
