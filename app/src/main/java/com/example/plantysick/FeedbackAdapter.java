package com.example.plantysick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyviewHolder>{


    private Context context;
    private ArrayList userName, feedback, rating, date;
    public FeedbackAdapter(Context context, ArrayList userName, ArrayList feedback,ArrayList rating, ArrayList date){
        this.context = context;
        this.userName = userName;
        this.feedback = feedback;
        this.rating = rating;
        this.date = date;
    }
    @NonNull
    @Override
    public FeedbackAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedback_post,parent,false);
        return new FeedbackAdapter.MyviewHolder(v);
     }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.MyviewHolder holder, int position) {
        holder.userName.setText(String.valueOf(userName.get(position)));
        holder.feedback.setText(String.valueOf(feedback.get(position)));
        holder.rating.setRating(Float.parseFloat(String.valueOf(rating.get(position))));
        holder.date.setText(String.valueOf(date.get(position)));

    }

    @Override
    public int getItemCount() {
        return userName.size();

    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView userName, date, feedback;
        RatingBar rating;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameFeedback);
            feedback = itemView.findViewById(R.id.feedback_input);
            rating = itemView.findViewById(R.id.ratingeBarFeedback);
            date =itemView.findViewById(R.id.feedback_date);

        }
    }

}
