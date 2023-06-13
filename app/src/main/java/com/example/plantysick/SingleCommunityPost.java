package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SingleCommunityPost extends AppCompatActivity {
    TextView username, date, title, description;
    RecyclerView commentContainer;
    EditText input;
    Button submit;
    Intent intent;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_community_post);
        username=  findViewById(R.id.username);
        date = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        commentContainer = findViewById(R.id.comment_container);
        input = findViewById(R.id.comment_input);
        submit = findViewById(R.id.comment_post);
        db = new DatabaseHelper(getApplicationContext());
        intent = getIntent();
        String usernameS = intent.getStringExtra("username");
        String titleS = intent.getStringExtra("title");
        String descS = intent.getStringExtra("description");
        String dateS = intent.getStringExtra("date");
        int postId = intent.getIntExtra("post_id", 0);
        title.setText(titleS);
        username.setText(usernameS);
        description.setText(descS);
        date.setText(dateS);

        displayComments(postId);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = input.getText().toString();
                SessionManagement ss = new SessionManagement(getApplicationContext());
                String user = ss.getSession();
                db.addComment(user, comment, postId);
                displayComments(postId);
            }
        });
    }

    private void displayComments(int postId) {
        Cursor result = db.getComments(postId);
        ArrayList<String> user, comment;
        user = new ArrayList<>();
        comment = new ArrayList<>();
        while(result.moveToNext()){
            user.add(result.getString(2));
            comment.add(result.getString(4));
            Log.i("knock", "displayComments: " + result.getString(4));
        }
        CommentAdapter adapter = new CommentAdapter(getApplicationContext(), user, comment);
        try {
            commentContainer.setAdapter(adapter);
            commentContainer.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
        catch (Exception e){
            Log.i("knock", "displayComments: " + e);
        }

    }
}