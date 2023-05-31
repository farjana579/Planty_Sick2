package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ask_Community extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_community);

        //getting the button id, edit text id from ask_community layout
        Button ask_community_button = findViewById(R.id.ask_Community_Button);
        EditText question_title = findViewById(R.id.question_title);
        EditText question_decription = findViewById(R.id.question_description);


        //shared preference


        //set  click listener for send data from ask_community to community layout
        ask_community_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String questionTitle = question_title.getText().toString();
                 String questionDescription = question_decription.getText().toString();
                 SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                 String userName = sessionManagement.getSession();

                 Intent community_intent = new Intent();
                 community_intent.putExtra("question_title", questionTitle);
                 community_intent.putExtra("question_description", questionDescription);
                 community_intent.putExtra("user_name", userName);
                 setResult(95, community_intent);
                 finish();
             }
         });


    }
}