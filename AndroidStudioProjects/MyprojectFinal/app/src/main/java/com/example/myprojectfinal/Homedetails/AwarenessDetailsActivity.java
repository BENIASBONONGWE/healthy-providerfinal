package com.example.myprojectfinal.Homedetails;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myprojectfinal.R;

public class AwarenessDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness_details);

        // Retrieve data from the intent
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        // Initialize views
        ImageView awarenessDetailsImage = findViewById(R.id.awarenessDetailsImage);
        TextView awarenessDetailsTitle = findViewById(R.id.awarenessDetailsTitle);
        TextView awarenessDetailsDescription = findViewById(R.id.awarenessDetailsDescription);

        // Set data to views
        awarenessDetailsImage.setImageResource(imageResource);
        awarenessDetailsTitle.setText(title);
        awarenessDetailsDescription.setText(description);
    }
}
