package com.example.myprojectfinal.Homedetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myprojectfinal.R;

public class NewsActivity extends AppCompatActivity {

    private ImageView newsImageHolder;
    private TextView newsTitle;
    private TextView newsDescription;
    private Button readMoreButton;

    private ImageView awarenessImageHolder;
    private TextView awarenessTitle;
    private TextView awarenessDescription;
    private Button readMoreAwarenessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Initialize views for News
        newsImageHolder = findViewById(R.id.newsImageHolder);
        newsTitle = findViewById(R.id.newsTitle);
        newsDescription = findViewById(R.id.newsDescription);
        readMoreButton = findViewById(R.id.readMoreButton);

        // Initialize views for Awareness
        awarenessImageHolder = findViewById(R.id.awarenessImageHolder);
        awarenessTitle = findViewById(R.id.awarenessTitle);
        awarenessDescription = findViewById(R.id.awarenessDescription);
        readMoreAwarenessButton = findViewById(R.id.readMoreAwarenessButton);

        // Set click listener for Read More button in News
        readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass data to NewsDetailsActivity
                Intent intent = new Intent(NewsActivity.this, NewsDetailsActivity.class);
                intent.putExtra("imageResource", R.drawable.doctor1);
                intent.putExtra("title", "Sample News Title");
                intent.putExtra("description", "This is a sample news description. It provides some information about the news.");
                startActivity(intent);
            }
        });

        // Set click listener for Read More button in Awareness
        readMoreAwarenessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass data to AwarenessDetailsActivity
                Intent intent = new Intent(NewsActivity.this, AwarenessDetailsActivity.class);
                intent.putExtra("imageResource", R.drawable.doc);
                intent.putExtra("title", "Sample Awareness Title");
                intent.putExtra("description", "This is a sample awareness description. It provides some information about the awareness.");
                startActivity(intent);
            }
        });
    }
}
