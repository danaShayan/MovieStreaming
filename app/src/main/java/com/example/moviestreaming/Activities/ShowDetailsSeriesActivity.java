package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviestreaming.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowDetailsSeriesActivity extends AppCompatActivity {


    TextView name, director, published, time, rate, genre, description;
    ImageView back, download, comment, img_movie;
    FloatingActionButton btn_play;
    RecyclerView cast, similar, seasons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_series);


        init();

    }

    private void init() {

        name = findViewById(R.id.name_movie);
        director = findViewById(R.id.name_director);
        published = findViewById(R.id.published);
        time = findViewById(R.id.time_movie);
        rate = findViewById(R.id.rate_movie);
        genre = findViewById(R.id.genre);
        description = findViewById(R.id.description);

        back = findViewById(R.id.back);
        download = findViewById(R.id.download);
        comment = findViewById(R.id.comment);
        img_movie = findViewById(R.id.img_movie);
        btn_play = findViewById(R.id.play);
        seasons = findViewById(R.id.seasons_recyclerView);
        cast = findViewById(R.id.cast_recyclerView);
        similar = findViewById(R.id.similar_recyclerView);

    }
}