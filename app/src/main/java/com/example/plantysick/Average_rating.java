package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class Average_rating extends AppCompatActivity {
    TextView show;
    RatingBar bar;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_rating);
        show = findViewById(R.id.show_rating);
        bar = findViewById(R.id.avg_rating_bar);
        database = new DatabaseHelper(getApplicationContext());
        double c = database.avgRating();
        show.setText(c + "");
        bar.setRating((float) c);
    }
}