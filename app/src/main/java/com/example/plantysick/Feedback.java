package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import java.time.LocalDate;

public class Feedback extends AppCompatActivity implements View.OnClickListener {


    Button good, average, bad, submit, rate;
    LinearLayout feedback_container, rate_container;
    EditText feedBack_BadInput, feedback_average_input, feedback_goodInput;
    DatabaseHelper databaseHelper;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        //button of secon linear layout whic is visible
        good = findViewById(R.id.feed_button_good);
        average = findViewById(R.id.feed_button_average);
        bad = findViewById(R.id.feed_button_bad);

        //last linear layout which contain RATINGBAR after clicl GOOD button
        rate_container = findViewById(R.id.rating_container);

        //3rd linearlayout which is invisible. Visible when BAD & AVERAGE button will be click
        feedback_container = findViewById(R.id.feedback_container);

        //3rd linearlayout, feedback_container edit_text & button
        submit = findViewById(R.id.feed_button_submit);
        feedBack_BadInput = findViewById(R.id.feedback_bad_input);

        //4th linearlayout, rating_container, editText, button, ratingBar
        feedback_goodInput = findViewById(R.id.feedback_good_input);
        rate = findViewById(R.id.feed_button_submit_rating);
        ratingBar = findViewById(R.id.feedback_rating);

        //set on click listener on Second Linear layout button GOOD, AVERAGE, BAD.
        good.setOnClickListener(this);
        average.setOnClickListener(this);
        bad.setOnClickListener(this);


        //Log.i("knock", "onCreate: " + databaseHelper.avgRating());

        //submit button to send data from activity to database
        submit.setOnClickListener(this);
        rate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //getting GOOD BUTTON id to visible the RATING_CONTAINER LINEARLAYOUT, 4th.
        if (view.getId() == R.id.feed_button_good) {
            rate_container.setVisibility(View.VISIBLE);
            feedback_container.setVisibility(View.GONE);
        }
        //end here

        //getting BAD & AVERAGE BUTTON id to visible the FEEDBACK_CONTAINER LINEARLAYOUT, 3rd.
        if (view.getId() == R.id.feed_button_bad) {
            feedback_container.setVisibility(View.VISIBLE);
            rate_container.setVisibility(View.GONE);
        }
        if (view.getId() == R.id.feed_button_average) {
            feedback_container.setVisibility(View.VISIBLE);
            rate_container.setVisibility(View.GONE);
        }
        //end here

        // Getting the SUBMIT button in 3rd linear layout, send data to database
        if (view.getId() == R.id.feed_button_submit) {

            //getting the INPUT from EDIT_TEXT which will after BAD AND AVERAGE button will click to set error
            String feedback_bad_input = feedBack_BadInput.getText().toString();
            if(feedback_bad_input.isEmpty()){
                feedBack_BadInput.setError("Please write your feedback");
                feedBack_BadInput.requestFocus();
                return;
            }
            //getting the error
            if (feedBack_BadInput.getError() == null) {
                String badInput = feedBack_BadInput.getText().toString();
                LocalDate date = null;
                //getting user name from sharedPreferences
                SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                String userName = sessionManagement.getSession();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = LocalDate.now();
                }
                databaseHelper.insertFeedback(userName, badInput, " ", date.toString());
               // Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();
            }
        }

        //end here

        //getting the RATE PLANTYSICK button in 4th linear layout, send rating to dataase
            if (view.getId() == R.id.feed_button_submit_rating) {
                String feed_good = feedback_goodInput.getText().toString();
                if (feed_good.isEmpty()) {
                    feedback_goodInput.setError("Please write your feedback");
                    feedback_goodInput.requestFocus();
                    return;
                }
                if (feedback_goodInput.getError() == null) {

                    String rating = ratingBar.getRating() + "";
                    Log.i("farja", "onClick: " + rating);
                    LocalDate date = null;

                    //getting user name from sharedPreferences
                    SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                    String userName = sessionManagement.getSession();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = LocalDate.now();
                    }
                    databaseHelper.insertFeedback(userName, feed_good, rating, date.toString());
                   // Toast.makeText(getApplicationContext(), "inserted1", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //end here
    }
