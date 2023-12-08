package com.example.myprojectfinal.Homedetails;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myprojectfinal.BuyMedicineActivity;
import com.example.myprojectfinal.DoctorDetails.BookDoctorActivity;
import com.example.myprojectfinal.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get references to the TextView and CardViews
        TextView titleHome = findViewById(R.id.titleHome);
        androidx.cardview.widget.CardView cardLabTest = findViewById(R.id.CardLabTest);
        androidx.cardview.widget.CardView cardBuyMedicine = findViewById(R.id.cardBuyMedicine);
        androidx.cardview.widget.CardView cardBookDoctor = findViewById(R.id.cardBookDoctor);
        androidx.cardview.widget.CardView cardNews = findViewById(R.id.cardNews);

        // Set click listeners for the TextView and CardViews



        cardBuyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToBuyMedicine();
            }
        });

        cardBookDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToBookDoctor();
            }
        });

        cardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToNews();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    private void navigateToBuyMedicine() {
        Intent intent = new Intent(this, BuyMedicineActivity.class);
        startActivity(intent);
    }

    private void navigateToBookDoctor() {
        Intent intent = new Intent(this, BookDoctorActivity.class);
        startActivity(intent);
    }

    private void navigateToNews() {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }
}
