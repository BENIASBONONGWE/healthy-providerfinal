package com.example.myprojectfinal.DoctorDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myprojectfinal.R;
import com.example.myprojectfinal.Validation.LoginActivity;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        // Get the booked details from the intent
        String selectedDate = getIntent().getStringExtra("selectedDate");
        String selectedTime = getIntent().getStringExtra("selectedTime");

        // Display the booked details in the activity
        TextView dateTextView = findViewById(R.id.bookedDateTextView);
        TextView timeTextView = findViewById(R.id.bookedTimeTextView);

        dateTextView.setText("Booked Date: " + selectedDate);
        timeTextView.setText("Booked Time: " + selectedTime);

        // Set click listener for the logout button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout here
                Intent intent = new Intent(AnotherActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it from the login page
            }
        });
    }
}
