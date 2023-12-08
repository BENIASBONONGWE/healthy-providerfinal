package com.example.myprojectfinal.DoctorDetails;// Import statements
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myprojectfinal.DoctorDetails.BookingDetailsActivity;
import com.example.myprojectfinal.R;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        // Set click listener for Container 1
        View container1 = findViewById(R.id.container1);
        container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookingDetails("Dr. John");
            }
        });

        // Set click listener for Container 2
        View container2 = findViewById(R.id.container2);
        container2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookingDetails("Dr. Benias");
            }
        });
    }

    private void openBookingDetails(String doctorName) {
        Intent intent = new Intent(this, BookingDetailsActivity.class);
        intent.putExtra("DOCTOR_NAME", doctorName);
        startActivity(intent);
    }
}
