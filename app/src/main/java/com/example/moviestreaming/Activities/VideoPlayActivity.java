package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.example.moviestreaming.R;

public class VideoPlayActivity extends AppCompatActivity {


    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        bundle = getIntent().getExtras();


    }
}