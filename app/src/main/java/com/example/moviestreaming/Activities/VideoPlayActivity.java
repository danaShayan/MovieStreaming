package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviestreaming.R;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;

public class VideoPlayActivity extends AppCompatActivity {


    BetterVideoPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        player = findViewById(R.id.videoPlayer);


        player.setSource(Uri.parse(ShowDetailMovieActivity.LINK_MOVIE));
        ShowDetailMovieActivity.LINK_MOVIE = "";
        player.setAutoPlay(true);
        player.start();


    }
}

