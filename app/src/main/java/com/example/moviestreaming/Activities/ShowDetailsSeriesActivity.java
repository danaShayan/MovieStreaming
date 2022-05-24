package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.CastAdapter;
import com.example.moviestreaming.Adapter.SeasonAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.Cast;
import com.example.moviestreaming.Model.Season;
import com.example.moviestreaming.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailsSeriesActivity extends AppCompatActivity {


    public static final String ID_DETAIL_ITEM = "";
    public static String NAME_DETAIL_ITEM = "name";
    public static String DIRECTOR_DETAIL_ITEM = "director";
    public static String RATE_IMDB_DETAIL_ITEM = "rate_imdb";
    public static String TIME_DETAIL_ITEM = "time";
    public static String PUBLISHED_DETAIL_ITEM = "published";
    public static String IMG_DETAIL_ITEM = "img";
    Bundle bundle;
    Global global;
    RequestQueue requestQueue;

    TextView name, director, published, time, rate, description;
    ImageView back, download, comment, img_movie;
    FloatingActionButton btn_play;
    RecyclerView cast, similar, seasons;
    List<Cast> list = new ArrayList<>();
    CastAdapter castAdapter;


    SeasonAdapter seasonAdapter;
    List<Season> seasonList = new ArrayList<>();
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_series);


        init();
        bundle();
        global.getShowDetail(this, requestQueue, id + "", description);
        playMovie();
        getCast();
        getSeason();

    }

    private void init() {
        global = new Global();
        requestQueue = Volley.newRequestQueue(this);

        name = findViewById(R.id.name_movie);
        director = findViewById(R.id.name_director);
        published = findViewById(R.id.published);
        time = findViewById(R.id.time_movie);
        rate = findViewById(R.id.rate_movie);
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

    private void bundle() {
        bundle = getIntent().getExtras();
        id = Integer.parseInt(bundle.getString(ID_DETAIL_ITEM));
        name.setText(bundle.getString(NAME_DETAIL_ITEM));
        director.setText(new StringBuilder("Director: " + bundle.getString(DIRECTOR_DETAIL_ITEM)));
        published.setText(new StringBuilder("Episodes: " + bundle.getString(PUBLISHED_DETAIL_ITEM)));
        time.setText(new StringBuilder("Seasons: " + bundle.getString(TIME_DETAIL_ITEM)));
        rate.setText(new StringBuilder("IMDb: " + bundle.getString(RATE_IMDB_DETAIL_ITEM)));
        Picasso.get().load(bundle.getString(IMG_DETAIL_ITEM)).into(img_movie);
    }

    private void playMovie() {

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ShowDetailMovieActivity.LINK_MOVIE.equals("")) {
                    startActivity(new Intent(ShowDetailsSeriesActivity.this, VideoPlayActivity.class));
                } else {
                    Toast.makeText(ShowDetailsSeriesActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCast() {

        cast.setHasFixedSize(true);
        cast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        castAdapter = new CastAdapter(this, list);
        global.getCast(this, requestQueue, id + "", cast, list);
        cast.setAdapter(castAdapter);
    }

    private void getSeason() {

        seasons.setHasFixedSize(true);
        seasons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        seasonAdapter = new SeasonAdapter(this, seasonList);
        global.getSeason(this, requestQueue, id + "", seasons, seasonList);
        seasons.setAdapter(seasonAdapter);

    }


}