package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_Feedback extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<String> userName, feedback, rating, date;
    FeedbackAdapter feedbackAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);

        databaseHelper = new DatabaseHelper(this);
        userName = new ArrayList<>();
        feedback = new ArrayList<>();
        rating = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_feedback);
        feedbackAdapter = new FeedbackAdapter(this, userName, feedback, rating, date);
        recyclerView.setAdapter(feedbackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayFeedback();
    }

    private void displayFeedback() {
        Cursor cursor = databaseHelper.getFeedbackdata();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();

        }
        else{
            while(cursor.moveToNext()){
                userName.add(cursor.getString(1));
                feedback.add(cursor.getString(2));
                rating.add(String.valueOf(cursor.getFloat(3)));
                date.add(cursor.getString(4));
                Toast.makeText(this, "full", Toast.LENGTH_SHORT).show();
            }
        }
    }
}