package com.example.plantysick;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewAdapter> {
    Context context;
    ArrayList<String> usernames, comments;

    public CommentAdapter(Context context, ArrayList<String> usernames, ArrayList<String> comments) {
        this.context = context;
        this.usernames = usernames;
        this.comments = comments;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.comment, parent, false);
        return new MyViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {
        holder.userTv.setText(String.valueOf(usernames.get(position)));
        holder.commentTv.setText(String.valueOf(comments.get(position)));
    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        TextView userTv, commentTv;

        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            userTv = itemView.findViewById(R.id.username_comment);
            commentTv = itemView.findViewById(R.id.comment);
        }
    }
}