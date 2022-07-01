package com.example.moviestreaming.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.AnimationAdapter;
import com.example.moviestreaming.Adapter.GenreAdapter;
import com.example.moviestreaming.Adapter.NewMovieAdapter;
import com.example.moviestreaming.Adapter.PopularMovieAdapter;
import com.example.moviestreaming.Adapter.SeriesAdapter;
import com.example.moviestreaming.Adapter.TopMovieIMDbAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.Animation;
import com.example.moviestreaming.Model.Genre;
import com.example.moviestreaming.Model.NewMovie;
import com.example.moviestreaming.Model.PopularMovie;
import com.example.moviestreaming.Model.Series;
import com.example.moviestreaming.Model.Slider;
import com.example.moviestreaming.Model.TopMovieIMDb;
import com.example.moviestreaming.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RequestQueue requestQueue;
    Global global;


    //Slider
    ViewPager slider_home;
    TabLayout tabs;
    List<Slider> listSlider = new ArrayList<>();

    //TopMovieIMDb
    List<TopMovieIMDb> listTopMovieIMDb = new ArrayList<>();
    RecyclerView recyclerView_topMovieIMDb;
    TopMovieIMDbAdapter topMovieIMDbAdapter;

    // New Movie

    List<NewMovie> listNewMovie = new ArrayList<>();
    NewMovieAdapter newMovieAdapter;
    RecyclerView recyclerView_newMovie;


    //Series
    List<Series> listSeries = new ArrayList<>();
    SeriesAdapter seriesAdapter;
    RecyclerView recyclerView_series;

    //Popular Movie
    List<PopularMovie> listPopularMovie = new ArrayList<>();
    PopularMovieAdapter popularMovieAdapter;
    RecyclerView recyclerView_popular;

    // Animation
    List<Animation> listAnimation = new ArrayList<>();
    AnimationAdapter animationAdapter;
    RecyclerView recyclerView_animation;


    // Genre
    List<Genre> listGenre = new ArrayList<>();
    GenreAdapter genreAdapter;
    RecyclerView recyclerView_genre;


    //Drawer Layout
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView btn_menu;
    RelativeLayout parent;

    // txt more
    TextView txt_more_genre, txt_more_top_movie_imdb, txt_more_new_movie, txt_more_series, txt_more_popular_movie, txt_more_animation;

    //KEY
    static String CATEGORY_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        getSlider();
        sliderTimer();
        getTopMovieIMDb();
        getNewMovie();
        getSeries();
        getPopularMovie();
        getAnimation();
        getGenre();
        getDrawer();
        getMoreListener();

    }

    private void init() {

        requestQueue = Volley.newRequestQueue(this);
        global = new Global();

        //Slider
        slider_home = findViewById(R.id.slider);
        tabs = findViewById(R.id.tabLayout);

        //TopMovieIMDb
        recyclerView_topMovieIMDb = findViewById(R.id.top_movie_imdb_recyclerView);

        //New Movie
        recyclerView_newMovie = findViewById(R.id.new_movie_recyclerView);

        //Series
        recyclerView_series = findViewById(R.id.series_recyclerView);

        //Popular Movie
        recyclerView_popular = findViewById(R.id.popular_recyclerView);

        //Animation
        recyclerView_animation = findViewById(R.id.animation_recyclerView);

        // Genre
        recyclerView_genre = findViewById(R.id.genre_recyclerView);

        //Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        btn_menu = findViewById(R.id.btn_menu);
        parent = findViewById(R.id.parent);

        // txt more
        txt_more_genre = findViewById(R.id.txt_more_genre);
        txt_more_top_movie_imdb = findViewById(R.id.txt_more_top_movie_imdb);
        txt_more_new_movie = findViewById(R.id.txt_more_new_movie);
        txt_more_popular_movie = findViewById(R.id.txt_more_popular_movie);
        txt_more_series = findViewById(R.id.txt_more_series);
        txt_more_animation = findViewById(R.id.txt_more_animation);


    }

    private void getSlider() {
        global.getSlider(HomeActivity.this, requestQueue, "", slider_home, listSlider);
        tabs.setupWithViewPager(slider_home);

    }

    private void sliderTimer() {

        TimerSlider timerSlider = new TimerSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerSlider, 6000, 3000);
    }

    private void getTopMovieIMDb() {

        recyclerView_topMovieIMDb.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_topMovieIMDb.setHasFixedSize(true);
        topMovieIMDbAdapter = new TopMovieIMDbAdapter(this, listTopMovieIMDb);
        recyclerView_topMovieIMDb.setAdapter(topMovieIMDbAdapter);
        global.getTopMovieIMDb(HomeActivity.this, requestQueue, "top_movie_new", recyclerView_topMovieIMDb, listTopMovieIMDb);


    }

    private void getNewMovie() {

        recyclerView_newMovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_newMovie.setHasFixedSize(true);
        newMovieAdapter = new NewMovieAdapter(this, listNewMovie);
        recyclerView_newMovie.setAdapter(newMovieAdapter);
        global.getNewMovie(this, requestQueue, "movie_new", recyclerView_newMovie, listNewMovie);
    }

    private void getSeries() {
        recyclerView_series.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_series.setHasFixedSize(true);
        seriesAdapter = new SeriesAdapter(this, listSeries);
        recyclerView_series.setAdapter(seriesAdapter);
        global.getSeries(this, requestQueue, "series", recyclerView_series, listSeries);


    }

    private void getPopularMovie() {
        recyclerView_popular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_popular.setHasFixedSize(true);
        popularMovieAdapter = new PopularMovieAdapter(this, listPopularMovie);
        recyclerView_popular.setAdapter(popularMovieAdapter);
        global.getPopularMovie(this, requestQueue, "popular_movie", recyclerView_popular, listPopularMovie);
    }

    private void getAnimation() {
        recyclerView_animation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_animation.setHasFixedSize(true);
        animationAdapter = new AnimationAdapter(this, listAnimation);
        recyclerView_animation.setAdapter(animationAdapter);
        global.getAnimation(this, requestQueue, "animation", recyclerView_animation, listAnimation);
    }

    private void getGenre() {
        recyclerView_genre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_genre.setHasFixedSize(true);
        genreAdapter = new GenreAdapter(this, listGenre);
        recyclerView_genre.setAdapter(genreAdapter);
        global.getGenre(this, requestQueue, "", recyclerView_genre, listGenre);
    }

    private void getDrawer() {

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                float changeScaleOffSet = slideOffset * (1 - 0.7f);
                float changeScaleOffSetY = slideOffset * (1 - 0.8f);
                float offSetScale = 1 - changeScaleOffSet;
                float offSetScaleY = 1 - changeScaleOffSetY;

                parent.setScaleX(offSetScale);
                parent.setScaleY(offSetScaleY);

                float X_OFF_SET = drawerView.getWidth() * slideOffset;
                float X_OFF_SET_CHANGE = parent.getWidth() * changeScaleOffSet;
                float X_TRANSLATION = X_OFF_SET - X_OFF_SET_CHANGE;
                parent.setTranslationX(X_TRANSLATION);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    private void getMoreListener() {
        txt_more_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, GenreActivity.class);
                startActivity(intent);

            }
        });
        txt_more_top_movie_imdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATEGORY_KEY = "top_movie_new";
                startActivity(new Intent(HomeActivity.this, TopMovieImdbActivity.class));

            }
        });
        txt_more_new_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        txt_more_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CATEGORY_KEY = "series";
                startActivity(new Intent(HomeActivity.this, SeriesActivity.class));

            }
        });
        txt_more_popular_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        txt_more_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                break;
            case R.id.nav_search:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                break;
            case R.id.nav_genre:
                startActivity(new Intent(HomeActivity.this, GenreActivity.class));
                break;
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public class TimerSlider extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slider_home.getCurrentItem() < listSlider.size() - 1) {
                        slider_home.setCurrentItem(slider_home.getCurrentItem() + 1);
                    } else {
                        slider_home.setCurrentItem(0);
                    }
                }
            });


        }
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();


    }
}