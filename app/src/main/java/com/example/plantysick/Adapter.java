package com.example.plantysick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyviewHolder> {

    private Context context;
    private ArrayList userName, questionTitle, questionDescription, date;
    Adapter adapter;
    public Adapter(Context context, ArrayList userName, ArrayList questionTitle, ArrayList questionDescription, ArrayList date){
        this.context = context;
        this.userName = userName;
        this.questionTitle= questionTitle;
        this.questionDescription = questionDescription;
        this.date = date;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.community_post,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
    holder.userName.setText(String.valueOf(userName.get(position)));
        holder.questionTitle.setText(String.valueOf(questionTitle.get(position)));
        holder.questionDescription.setText(String.valueOf(questionDescription.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));

    }

    @Override
    public int getItemCount() {

        return userName.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView userName, questionTitle, questionDescription, date;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNamePost);
            questionTitle = itemView.findViewById(R.id.question_title_post);
            questionDescription = itemView.findViewById(R.id.question_description_post);
            date =itemView.findViewById(R.id.date);
        }
    }
    public void onUpdateContent(ArrayList user, ArrayList title,ArrayList description,ArrayList postDate){
        userName.clear();
        questionTitle.clear();
        questionDescription.clear();
        date.clear();
        userName.addAll(user);
        questionTitle.addAll(title);
        questionDescription.addAll(description);
        date.addAll(postDate);

    }
}
