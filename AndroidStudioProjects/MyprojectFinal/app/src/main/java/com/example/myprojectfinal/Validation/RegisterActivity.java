package com.example.myprojectfinal.Validation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.myprojectfinal.Databases.Database;
import com.example.myprojectfinal.R;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    Button registrationButton;
    TextView headerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.registrationPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registrationButton = findViewById(R.id.registrationButton);
        headerTextView = findViewById(R.id.headerTextView);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                Database db= new Database(getApplicationContext(),"healthcare",null,1);

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                } else {
                    db.register(name,email,password);
                    // Registration logic (you can implement your registration process here)
                    // After successful registration, you can navigate to another activity
                    // Example: startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

        headerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}