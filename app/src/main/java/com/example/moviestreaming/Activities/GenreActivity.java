package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.GenreCompleteAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.Genre;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Global global = new Global();
        List<Genre> list = new ArrayList<>();
        RecyclerView recyclerView;
        GenreCompleteAdapter genreCompleteAdapter;
        ImageView imageView = findViewById(R.id.back);


        recyclerView = findViewById(R.id.genre_recyclerViewComplete);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        genreCompleteAdapter = new GenreCompleteAdapter(this, list);
        recyclerView.setAdapter(genreCompleteAdapter);
        global.getGenreComplete(this, requestQueue, "", recyclerView, list);

        imageView.setOnClickListener(view -> {
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}