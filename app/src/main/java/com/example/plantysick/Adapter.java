package com.example.plantysick;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyviewHolder> {

    private Context context;
    private ArrayList userName, questionTitle, questionDescription, date, ids;
    Adapter adapter;
    DatabaseHelper db;
    private int react = 0;
    public Adapter(Context context, ArrayList userName, ArrayList questionTitle, ArrayList questionDescription, ArrayList date, ArrayList ids){
        this.context = context;
        this.userName = userName;
        this.questionTitle= questionTitle;
        this.questionDescription = questionDescription;
        this.date = date;
        this.ids = ids;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.community_post,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

            int pid = Integer.valueOf(ids.get(position).toString());
            int count = db.getReactSum(pid);
        holder.reactCount.setText(""+count);
    holder.userName.setText(String.valueOf(userName.get(position)));
    react = db.getReact(Integer.valueOf(ids.get(position).toString()), userName.get(position).toString());
    if(react == -1){
        holder.downvote.setChecked(true);
        holder.upvote.setButtonDrawable(R.drawable.ic_baseline_thumb_up_off_alt_24);
        holder.downvote.setButtonDrawable(R.drawable.icon_thumb_down);
    }else if(react == 1){
        holder.upvote.setChecked(true);
        holder.downvote.setButtonDrawable(R.drawable.ic_baseline_thumb_down_off_alt_24);
        holder.upvote.setButtonDrawable(R.drawable.icon_thumb_up);
    }
    else{
        holder.downvote.setButtonDrawable(R.drawable.ic_baseline_thumb_down_off_alt_24);
        holder.upvote.setButtonDrawable(R.drawable.ic_baseline_thumb_up_off_alt_24);
    }
    holder.upvote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                int current = Integer.valueOf(holder.reactCount.getText().toString());
                if(!holder.downvote.isChecked())
                    current += 2;
                else{
                    current += 1;
                }
                holder.reactCount.setText(current + "");
                holder.downvote.setButtonDrawable(R.drawable.ic_baseline_thumb_down_off_alt_24);
                holder.upvote.setButtonDrawable(R.drawable.icon_thumb_up);
                holder.downvote.setChecked(false);
                db.addReact(Integer.valueOf(ids.get(position).toString()), userName.get(position).toString(), 1);
            }else{
                int current = count - 1 ;
                holder.reactCount.setText(current + "");
                holder.upvote.setButtonDrawable(R.drawable.ic_baseline_thumb_up_off_alt_24);
                db.addReact(Integer.valueOf(ids.get(position).toString()), userName.get(position).toString(), 0);
            }
        }
    });
    holder.downvote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                int current = Integer.valueOf(holder.reactCount.getText().toString());
                if(!holder.upvote.isChecked())
                    current -= 2;
                else{
                    current -= 1;
                }
                holder.reactCount.setText(current + "");
                holder.upvote.setButtonDrawable(R.drawable.ic_baseline_thumb_up_off_alt_24);
                holder.upvote.setChecked(false);
                holder.downvote.setButtonDrawable(R.drawable.icon_thumb_down);
                db.addReact(Integer.valueOf(ids.get(position).toString()), userName.get(position).toString(), -1);
            }else{

                int current = count + 1 ;
                holder.reactCount.setText(current + "");

                holder.downvote.setButtonDrawable(R.drawable.ic_baseline_thumb_down_off_alt_24);
                db.addReact(Integer.valueOf(ids.get(position).toString()), userName.get(position).toString(), 0);
            }
        }
    });
        holder.questionTitle.setText(String.valueOf(questionTitle.get(position)));
        holder.questionDescription.setText(String.valueOf(questionDescription.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SingleCommunityPost.class);
                i.putExtra("username", userName.get(position).toString());
                i.putExtra("date", date.get(position).toString());
                i.putExtra("title", questionTitle.get(position).toString());
                i.putExtra("description", questionDescription.get(position).toString());
                i.putExtra("post_id", Integer.valueOf(ids.get(position).toString()));

                context.startActivity(i);
            }
        });

        holder.questionTitle.setText(String.valueOf(questionTitle.get(position)));
        holder.questionDescription.setText(String.valueOf(questionDescription.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SingleCommunityPost.class);
                i.putExtra("username", userName.get(position).toString());
                i.putExtra("date", date.get(position).toString());
                i.putExtra("title", questionTitle.get(position).toString());
                i.putExtra("description", questionDescription.get(position).toString());
                i.putExtra("post_id", Integer.valueOf(ids.get(position).toString()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return userName.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView userName, questionTitle, questionDescription, date, reactCount;
        ToggleButton upvote, downvote;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNamePost);
            questionTitle = itemView.findViewById(R.id.question_title_post);
            questionDescription = itemView.findViewById(R.id.question_description_post);
            date =itemView.findViewById(R.id.date);
            upvote = itemView.findViewById(R.id.upvote);
            downvote = itemView.findViewById(R.id.downvote);
            reactCount = itemView.findViewById(R.id.react_count);
            db = new DatabaseHelper(context);
        }
    }
}
