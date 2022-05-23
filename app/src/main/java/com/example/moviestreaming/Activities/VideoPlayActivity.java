package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moviestreaming.R;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;

public class VideoPlayActivity extends AppCompatActivity {


    Bundle bundle;
    BetterVideoPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        bundle = getIntent().getExtras();
        player = findViewById(R.id.videoPlayer);

        player.setSource(Uri.parse(ShowDetailMovieActivity.LINK_MOVIE));
        player.setAutoPlay(true);
        player.start();



    }
}