package com.example.plantysick;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class Community extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<String> userName, questionTitle, questionDescription, date;
    ArrayList<Integer> ids;
    Adapter adapter;
    Button prev, next, page;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        databaseHelper = new DatabaseHelper(this);

        userName = new ArrayList<>();
        questionTitle = new ArrayList<>();
        questionDescription = new ArrayList<>();
        date = new ArrayList<>();
         ids = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        prev = findViewById(R.id.previous_btn);
        next = findViewById(R.id.next_btn);
        page = findViewById(R.id.show_page_nmbr);
        prev.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int pageNo = Integer.parseInt(page.getText().toString());
                 if (pageNo == 1) {
                     return;
                 }
                 pageNo -= 1;
                 displayPost(pageNo);
                 page.setText(pageNo + "");
             }
         });
         next.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int pageNo = Integer.parseInt(page.getText().toString());
                 pageNo += 1;
                 page.setText(pageNo + "");
                 displayPost(pageNo);
             }
         });

       displayPost(1);
        ExtendedFloatingActionButton floatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.ask_Community_Button);
         floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ask_Community.class);
                startActivityForResult(intent, 95);
            }
        });
     }

    private void displayPost(int pageNo) {
        Cursor cursor = databaseHelper.getdata(pageNo);
        if(cursor.getCount()==0){

        }
        else{
            userName.clear();
            questionTitle.clear();
            date.clear();
            ids.clear();
            questionDescription.clear();
            while(cursor.moveToNext()){
                userName.add(cursor.getString(1));
                questionTitle.add(cursor.getString(2));
                questionDescription.add(cursor.getString(3));
                date.add(cursor.getString(4));
                ids.add(cursor.getInt(0));
            }
            if(ids.size()<10){
                next.setEnabled(false);
            }
            else{
                next.setEnabled(true);
            }
            adapter = new Adapter(this, userName, questionTitle, questionDescription,date, ids);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
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


