package com.example.plantysick;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class Community extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<String> userName, questionTitle, questionDescription, date;
    Adapter adapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        databaseHelper = new DatabaseHelper(this);
        userName = new ArrayList<>();
        questionTitle = new ArrayList<>();
        questionDescription = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new Adapter(this, userName, questionTitle, questionDescription,date);
      recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       displayPost();
        ExtendedFloatingActionButton floatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.ask_Community_Button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ask_Community.class);
                startActivityForResult(intent, 95);
            }
        });
     }

    private void displayPost() {
        Cursor cursor = databaseHelper.getdata();
        if(cursor.getCount()==0){

        }
        else{
            while(cursor.moveToNext()){
                userName.add(cursor.getString(1));
                questionTitle.add(cursor.getString(2));
                questionDescription.add(cursor.getString(3));
                date.add(cursor.getString(4));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            LocalDate date = null;
            if(requestCode == 95){
                String userName = data.getStringExtra("user_name");
                String questionTilte = data.getStringExtra("question_title");
                String questionDescription = data.getStringExtra("question_description");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = LocalDate.now();
                }
                databaseHelper.insertPost(userName, questionTilte, questionDescription, date.toString());
             }
        }
    }


