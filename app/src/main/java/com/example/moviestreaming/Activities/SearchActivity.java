package com.example.moviestreaming.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.moviestreaming.R;

public class SearchActivity extends AppCompatActivity {


    CardView topNewMovie, series, animation, newMovie, all;

    public static String CATE_NAME = "cate_name";
    public static String TITLE = "title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        init();
        setListener();

    }


    private void setListener() {
        topNewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATE_NAME = "top_movie_new";
                TITLE = "Top Movie IMDb";
                startActivity(new Intent(SearchActivity.this, ShowSearchActivity.class));
            }
        });
        series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATE_NAME = "series";
                TITLE = "Series";
                startActivity(new Intent(SearchActivity.this, ShowSearchActivity.class));
            }
        });
        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATE_NAME = "animation";
                TITLE = "Animation";
                startActivity(new Intent(SearchActivity.this, ShowSearchActivity.class));
            }
        });
        newMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATE_NAME = "movie_new";
                TITLE = "New Movie";
                startActivity(new Intent(SearchActivity.this, ShowSearchActivity.class));
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TITLE = "All Movie";
                startActivity(new Intent(SearchActivity.this, ShowSearchActivity.class));
            }
        });


    }

    private void init() {
        all = findViewById(R.id.card_all);
        topNewMovie = findViewById(R.id.card_top_movie_imdb);
        series = findViewById(R.id.card_series);
        animation = findViewById(R.id.card_animation);
        newMovie = findViewById(R.id.card_new_movie);

    }
}