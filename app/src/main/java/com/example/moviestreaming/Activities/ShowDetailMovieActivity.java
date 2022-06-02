
package com.example.moviestreaming.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.CastAdapter;
import com.example.moviestreaming.Adapter.RandomAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.AllInformation;
import com.example.moviestreaming.Model.Cast;
import com.example.moviestreaming.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailMovieActivity extends AppCompatActivity {

    public static String ID_DETAIL_ITEM = "";
    public static final String CATEGORY_NAME = "category_name";
    public static String NAME_DETAIL_ITEM = "name";
    public static String DIRECTOR_DETAIL_ITEM = "director";
    public static String RATE_IMDB_DETAIL_ITEM = "rate_imdb";
    public static String TIME_DETAIL_ITEM = "time";
    public static String PUBLISHED_DETAIL_ITEM = "published";
    public static String IMG_DETAIL_ITEM = "img";
    public static String GENRE_DETAIL_ITEM = "genre";
    public static String LINK_MOVIE = "";


    RequestQueue requestQueue;
    Global global;

    TextView name, director, published, time, rate, genre, description;
    ImageView back, download, comment, img_movie;
    FloatingActionButton btn_play;
    RecyclerView cast, similar;
    List<Cast> castList = new ArrayList<>();
    CastAdapter castAdapter;
    Bundle bundle;
    int id;
    String categoryName;


    // Random Movie
    List<AllInformation> listRandom = new ArrayList<>();
    RandomAdapter randomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_movie);


        init();
        bundle();
        global.getShowDetail(this, requestQueue, id + "", description);
        getCast();
        playMovie();
        getRandom();

    }


    private void init() {

        requestQueue = Volley.newRequestQueue(this);
        global = new Global();

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
        cast = findViewById(R.id.cast_recyclerView);
        similar = findViewById(R.id.similar_recyclerView);

    }

    @SuppressLint("SetTextI18n")
    private void bundle() {
        bundle = getIntent().getExtras();
        id = Integer.parseInt(bundle.getString(ID_DETAIL_ITEM));
        categoryName = bundle.getString(CATEGORY_NAME);
        name.setText(bundle.getString(NAME_DETAIL_ITEM));
        director.setText(new StringBuilder("Director: " + bundle.getString(DIRECTOR_DETAIL_ITEM)));
        published.setText(new StringBuilder("Published: " + bundle.getString(PUBLISHED_DETAIL_ITEM)));
        time.setText(bundle.getString(TIME_DETAIL_ITEM));
        rate.setText(new StringBuilder("IMDb " + bundle.getString(RATE_IMDB_DETAIL_ITEM)));
        Picasso.get().load(bundle.getString(IMG_DETAIL_ITEM)).into(img_movie);
        genre.setText(bundle.getString("genre"));


        if (genre.length() == 0) {
            genre.setText("No Genre Set");
        }

    }

    private void getCast() {

        cast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cast.setHasFixedSize(true);
        castAdapter = new CastAdapter(this, castList);
        cast.setAdapter(castAdapter);
        global.getCast(this, requestQueue, id + "", cast, castList);

    }

    private void playMovie() {

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ShowDetailMovieActivity.LINK_MOVIE.equals("")) {
                    Intent intent = new Intent(ShowDetailMovieActivity.this, VideoPlayActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ShowDetailMovieActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void getRandom() {
        similar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        similar.setHasFixedSize(true);
        randomAdapter = new RandomAdapter(listRandom, this);
        similar.setAdapter(randomAdapter);
        global.getRandom(this, requestQueue, categoryName, similar, listRandom);
    }

}