package com.example.myprojectfinal.DoctorDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myprojectfinal.R;
import com.example.myprojectfinal.Validation.LoginActivity;

public class BookDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        // Find views by their IDs
        CardView physicianCard = findViewById(R.id.Physisian);
        CardView dieticianCard = findViewById(R.id.Dietician);
        CardView segmentCard = findViewById(R.id.FDSegion);
        CardView malariaCard = findViewById(R.id.FDMalaria);
        CardView logoutCard = findViewById(R.id.FDLogout);
        CardView hivTreatmentCard = findViewById(R.id.FDHivTreatment);

        // Set click listeners for each card
        physicianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on Physician");
                // Start PhysicianActivity when the Physician card is clicked
                Intent intent = new Intent(BookDoctorActivity.this, PhysicianActivity.class);
                startActivity(intent);
            }
        });

        dieticianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on Dietician");
                Intent intent = new Intent(BookDoctorActivity.this, DieticianActivity.class);
                startActivity(intent);
            }
        });

        segmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on Segment");
                Intent intent = new Intent(BookDoctorActivity.this,SegionActivity.class);
                startActivity(intent);
            }
        });

        malariaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on Malaria");
                Intent intent = new Intent(BookDoctorActivity.this,DieticianActivity.class);
                startActivity(intent);
            }
        });

        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on Logout");

                // Start the LoginActivity
                Intent intent = new Intent(BookDoctorActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                // Finish the current activity to prevent the user from going back to it after logout

            }
        });


        hivTreatmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Clicked on HIV Treatment");
                Intent intent = new Intent(BookDoctorActivity.this,HIVTreatmentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
