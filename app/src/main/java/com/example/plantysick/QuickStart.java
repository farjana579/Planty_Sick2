package com.example.plantysick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class QuickStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_start);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        YouTubePlayerView youTubePlayerView_2 = findViewById(R.id.youtube_player_view_2);

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
             public void onReady(@NonNull YouTubePlayer youTubePlayer) {
               // String videoId = "lywgdfE_HuE";
                //youTubePlayer.loadVideo(videoId, 0);
                 playVideo(youTubePlayer,"lywgdfE_HuE");
             }
         });
        youTubePlayerView_2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
               playVideo(youTubePlayer, "_37ggUj9h2M");
            }
        });

    }
    public void playVideo(YouTubePlayer youTubePlayer, String video){
        String videoId = video;
        youTubePlayer.loadVideo(videoId, 0);
        youTubePlayer.pause();

    }
}