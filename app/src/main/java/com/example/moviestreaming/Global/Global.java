package com.example.moviestreaming.Global;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.moviestreaming.Activities.ShowDetailMovieActivity;
import com.example.moviestreaming.Adapter.AnimationAdapter;
import com.example.moviestreaming.Adapter.CastAdapter;
import com.example.moviestreaming.Adapter.EpisodeAdapter;
import com.example.moviestreaming.Adapter.GenreAdapter;
import com.example.moviestreaming.Adapter.GenreCompleteAdapter;
import com.example.moviestreaming.Adapter.NewMovieAdapter;
import com.example.moviestreaming.Adapter.PopularMovieAdapter;
import com.example.moviestreaming.Adapter.RandomAdapter;
import com.example.moviestreaming.Adapter.RandomSeriesAdapter;
import com.example.moviestreaming.Adapter.SearchAdapter;
import com.example.moviestreaming.Adapter.SeasonAdapter;
import com.example.moviestreaming.Adapter.SeriesAdapter;
import com.example.moviestreaming.Adapter.SeriesCompleteAdapter;
import com.example.moviestreaming.Adapter.ShowGenreAdapter;
import com.example.moviestreaming.Adapter.SliderAdapter;
import com.example.moviestreaming.Adapter.TopMovieIMDbAdapter;
import com.example.moviestreaming.Adapter.TopMovieIMDbCompleteAdapter;
import com.example.moviestreaming.Model.AllInformation;
import com.example.moviestreaming.Model.Animation;
import com.example.moviestreaming.Model.Cast;
import com.example.moviestreaming.Model.Episode;
import com.example.moviestreaming.Model.Genre;
import com.example.moviestreaming.Model.NewMovie;
import com.example.moviestreaming.Model.PopularMovie;
import com.example.moviestreaming.Model.Season;
import com.example.moviestreaming.Model.Series;
import com.example.moviestreaming.Model.ShowGenre;
import com.example.moviestreaming.Model.Slider;
import com.example.moviestreaming.Model.TopMovieIMDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Global {


    String LINK = "http://192.168.43.187/moviestreaming/getSlider.php";
    RequestQueue requestQueue;
    List<Slider> listSlider = new ArrayList<>();
    SliderAdapter sliderAdapter;


    //TopMovieIMDb
    List<TopMovieIMDb> listTopMovieIMDb = new ArrayList<>();
    TopMovieIMDbAdapter topMovieIMDbAdapter;
    String link_home_information = "http://192.168.43.187/moviestreaming//getInformationHome.php?category_name=";


    //New Movie
    List<NewMovie> listNewMovie = new ArrayList<>();
    NewMovieAdapter newMovieAdapter;

    //Series
    List<Series> listSeries = new ArrayList<>();
    SeriesAdapter seriesAdapter;

    //Popular Movie
    List<PopularMovie> listPopularMovie = new ArrayList<>();
    PopularMovieAdapter popularMovieAdapter;

    ///Animation
    List<Animation> listAnimation = new ArrayList<>();
    AnimationAdapter animationAdapter;

    // Genre
    List<Genre> listGenre = new ArrayList<>();
    GenreAdapter genreAdapter;
    String link_genre = "http://192.168.43.187/moviestreaming/getGenre.php";

    //Genre
    GenreCompleteAdapter genreCompleteAdapter;


    // All Top Movie IMDb
    String link_all_information_home = "http://192.168.43.187/moviestreaming/getAllInformationHome.php?category_name=";
    TopMovieIMDbCompleteAdapter topMovieIMDbCompleteAdapter;
    SeriesCompleteAdapter seriesCompleteAdapter;


    // Show_Detail
    String link_show_detail = "http://192.168.43.187/moviestreaming/show_detail.php?id_item=";


    //Cast
    List<Cast> castList = new ArrayList<>();
    CastAdapter castAdapter;
    String linkCast = "http:/192.168.43.187/moviestreaming/getCast.php?id_item=";


    //Season
    List<Season> seasonList = new ArrayList<>();
    SeasonAdapter seasonAdapter;
    String linkSeason = "http://192.168.43.187/moviestreaming/getSeason.php?id_item=";

    //Episode
    List<Episode> episodeList = new ArrayList<>();
    EpisodeAdapter episodeAdapter;
    String linkEpisode = "http://192.168.43.187/moviestreaming/getEpisode.php?id_season=";

    //Random
    List<AllInformation> randomList = new ArrayList<>();
    RandomAdapter randomAdapter;
    RandomSeriesAdapter randomSeriesAdapter;
    String randomLink = "http://192.168.43.187/moviestreaming/getAllInformationHome.php?category_name=";

    // Show Global
    List<ShowGenre> showGenreList = new ArrayList<>();
    ShowGenreAdapter showGenreAdapter;
    String linkShowGenre = "http://192.168.43.187/moviestreaming/get_show_genre.php?genre_name=";

    //search
    List<AllInformation> searchList = new ArrayList<>();
    SearchAdapter searchAdapter;
    String linkSearch = "http://192.168.43.187/moviestreaming/getSearch.php?category_name=";


    public void getSlider(final Context context, RequestQueue requestQueue, String url, ViewPager viewPager, List<Slider> list) {

        this.requestQueue = requestQueue;
        this.LINK = LINK + url;
        this.listSlider = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("slider");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Slider slider = new Slider();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");

                    slider.setId(id);
                    slider.setName(name);
                    slider.setLink_img(link_img);
                    slider.setTime(time);
                    slider.setPublished(published);

                    listSlider.add(slider);
                    sliderAdapter = new SliderAdapter(context, listSlider);
                    viewPager.setAdapter(sliderAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public void getTopMovieIMDb(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<TopMovieIMDb> list) {

        this.requestQueue = requestQueue;
        this.LINK = link_home_information + url;
        this.listTopMovieIMDb = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("movie_streaming");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TopMovieIMDb topMovieIMDb = new TopMovieIMDb();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");
                    String director = jsonObject.getString("director");
                    String rate_imdb = jsonObject.getString("rate_imdb");
                    String category_name = jsonObject.getString("catagory_name");
                    String rank = jsonObject.getString("rank");
                    String genre = jsonObject.getString("genre_name");


                    topMovieIMDb.setId(id);
                    topMovieIMDb.setName(name);
                    topMovieIMDb.setLink_img(link_img);
                    topMovieIMDb.setTime(time);
                    topMovieIMDb.setPublished(published);
                    topMovieIMDb.setDirector(director);
                    topMovieIMDb.setRate_imdb(rate_imdb);
                    topMovieIMDb.setCategory_name(category_name);
                    topMovieIMDb.setRank(rank);
                    topMovieIMDb.setGenre(genre);

                    listTopMovieIMDb.add(topMovieIMDb);
                    topMovieIMDbAdapter = new TopMovieIMDbAdapter(context, listTopMovieIMDb);
                    recyclerView.setAdapter(topMovieIMDbAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("getTop", LINK));
        requestQueue.add(request);
    }

    public void getNewMovie(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<NewMovie> list) {

        this.requestQueue = requestQueue;
        this.LINK = link_home_information + url;
        this.listNewMovie = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("movie_streaming");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    NewMovie newMovie = new NewMovie();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");
                    String director = jsonObject.getString("director");
                    String rate_imdb = jsonObject.getString("rate_imdb");
                    String category_name = jsonObject.getString("catagory_name");
                    String rank = jsonObject.getString("rank");


                    newMovie.setId(id);
                    newMovie.setName(name);
                    newMovie.setLink_img(link_img);
                    newMovie.setTime(time);
                    newMovie.setPublished(published);
                    newMovie.setDirector(director);
                    newMovie.setRate_imdb(rate_imdb);
                    newMovie.setCategory_name(category_name);

                    listNewMovie.add(newMovie);
                    newMovieAdapter = new NewMovieAdapter(context, listNewMovie);
                    recyclerView.setAdapter(newMovieAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        requestQueue.add(request);
    }

    public void getSeries(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Series> list) {

        this.requestQueue = requestQueue;
        this.LINK = link_home_information + url;
        this.listSeries = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("movie_streaming");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Series series = new Series();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");
                    String director = jsonObject.getString("director");
                    String rate_imdb = jsonObject.getString("rate_imdb");
                    String category_name = jsonObject.getString("catagory_name");
                    String rank = jsonObject.getString("rank");


                    series.setId(id);
                    series.setName(name);
                    series.setLink_img(link_img);
                    series.setTime(time);
                    series.setPublished(published);
                    series.setDirector(director);
                    series.setRate_imdb(rate_imdb);
                    series.setCategory_name(category_name);

                    listSeries.add(series);
                    seriesAdapter = new SeriesAdapter(context, listSeries);
                    recyclerView.setAdapter(seriesAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }

    public void getPopularMovie(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<PopularMovie> list) {

        this.requestQueue = requestQueue;
        this.LINK = link_home_information + url;
        this.listPopularMovie = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PopularMovie popularMovie = new PopularMovie();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");
                        String rank = jsonObject.getString("rank");


                        popularMovie.setId(id);
                        popularMovie.setName(name);
                        popularMovie.setLink_img(link_img);
                        popularMovie.setTime(time);
                        popularMovie.setPublished(published);
                        popularMovie.setDirector(director);
                        popularMovie.setRate_imdb(rate_imdb);
                        popularMovie.setCategory_name(category_name);

                        listPopularMovie.add(popularMovie);
                        popularMovieAdapter = new PopularMovieAdapter(context, listPopularMovie);
                        recyclerView.setAdapter(popularMovieAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }

    public void getAnimation(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Animation> list) {

        this.requestQueue = requestQueue;
        this.LINK = link_home_information + url;
        this.listAnimation = list;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Animation animation = new Animation();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");
                        String rank = jsonObject.getString("rank");


                        animation.setId(id);
                        animation.setName(name);
                        animation.setLink_img(link_img);
                        animation.setTime(time);
                        animation.setPublished(published);
                        animation.setDirector(director);
                        animation.setRate_imdb(rate_imdb);
                        animation.setCategory_name(category_name);

                        listAnimation.add(animation);
                        animationAdapter = new AnimationAdapter(context, listAnimation);
                        recyclerView.setAdapter(animationAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }

    public void getGenre(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Genre> list) {

        this.requestQueue = requestQueue;
        this.link_genre = link_genre + url;
        this.listGenre = list;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, link_genre, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = jsonArray.length() - 5; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Genre genre = new Genre();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");

                        genre.setId(id);
                        genre.setLink_img(link_img);
                        genre.setName(name);

                        listGenre.add(genre);
                        genreAdapter = new GenreAdapter(context, listGenre);
                        recyclerView.setAdapter(genreAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public void getGenreComplete(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Genre> list) {

        this.requestQueue = requestQueue;
        this.link_genre = link_genre + url;
        this.listGenre = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, link_genre, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Genre genre = new Genre();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");

                        genre.setId(id);
                        genre.setName(name);
                        genre.setLink_img(link_img);

                        listGenre.add(genre);
                        genreCompleteAdapter = new GenreCompleteAdapter(context, listGenre);
                        recyclerView.setAdapter(genreCompleteAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }

    public void getTopMovieIMDbComplete(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<TopMovieIMDb> list) {

        this.requestQueue = requestQueue;
        String LINK = link_all_information_home + url;
        this.listTopMovieIMDb = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TopMovieIMDb topMovieIMDb = new TopMovieIMDb();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");
                        String rank = jsonObject.getString("rank");
                        String genre = jsonObject.getString("genre_name");

                        topMovieIMDb.setId(id);
                        topMovieIMDb.setName(name);
                        topMovieIMDb.setLink_img(link_img);
                        topMovieIMDb.setTime(time);
                        topMovieIMDb.setPublished(published);
                        topMovieIMDb.setDirector(director);
                        topMovieIMDb.setRate_imdb(rate_imdb);
                        topMovieIMDb.setCategory_name(category_name);
                        topMovieIMDb.setRank(rank);
                        topMovieIMDb.setGenre(genre);


                        listTopMovieIMDb.add(topMovieIMDb);
                        topMovieIMDbCompleteAdapter = new TopMovieIMDbCompleteAdapter(context, listTopMovieIMDb);
                        recyclerView.setAdapter(topMovieIMDbCompleteAdapter);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(request);
    }

    public void getSeriesComplete(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Series> list) {

        this.requestQueue = requestQueue;
        String LINK = link_all_information_home + url;
        this.listSeries = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Series series = new Series();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");


                        series.setId(id);
                        series.setName(name);
                        series.setLink_img(link_img);
                        series.setTime(time);
                        series.setPublished(published);
                        series.setDirector(director);
                        series.setRate_imdb(rate_imdb);
                        series.setCategory_name(category_name);


                        listSeries.add(series);
                        seriesCompleteAdapter = new SeriesCompleteAdapter(context, listSeries);
                        recyclerView.setAdapter(seriesCompleteAdapter);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(request);
    }

    public void getShowDetail(Context context, RequestQueue requestQueue, String url, TextView description) {

        this.requestQueue = requestQueue;
        String LINK = link_show_detail + url;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String descriptionTXT = jsonObject.getString("description");
                        ShowDetailMovieActivity.LINK_MOVIE = jsonObject.getString("link_movie");
                        description.setText(descriptionTXT);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public void getCast(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Cast> list) {

        this.castList = list;
        this.requestQueue = requestQueue;
        this.linkCast = linkCast + url;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, linkCast, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Cast cast = new Cast();

                        cast.setId(jsonObject.getString("id"));
                        cast.setId_item(jsonObject.getString("id_item"));
                        cast.setLink_img(jsonObject.getString("link_img"));
                        cast.setName(jsonObject.getString("name"));

                        castList.add(cast);
                        castAdapter = new CastAdapter(context, castList);
                        recyclerView.setAdapter(castAdapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public void getSeason(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Season> list) {

        this.requestQueue = requestQueue;
        this.seasonList = list;
        this.LINK = linkSeason + url;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Season season = new Season();

                        String id = jsonObject.getString("id");
                        String id_item = jsonObject.getString("id_item");
                        String number_season = jsonObject.getString("number_season");
                        String count_episodes = jsonObject.getString("count_episodes");
                        String link_img_season = jsonObject.getString("link_img_season");

                        season.setId(id);
                        season.setId_item(id_item);
                        season.setCount_episodes(count_episodes);
                        season.setNumber_season(number_season);
                        season.setLink_img_seaon(link_img_season);

                        list.add(season);
                        seasonAdapter = new SeasonAdapter(context, list);
                        recyclerView.setAdapter(seasonAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public void getEpisode(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<Episode> list) {

        this.requestQueue = requestQueue;
        this.episodeList = list;
        this.linkEpisode = linkEpisode + url;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, linkEpisode, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Episode episode = new Episode();

                        episode.setDetail(jsonObject.getString("detail"));
                        episode.setId(jsonObject.getString("id"));
                        episode.setId_season(jsonObject.getString("id_season"));
                        episode.setLink_img(jsonObject.getString("link_img"));
                        episode.setName(jsonObject.getString("name"));
                        episode.setLink_play_episode(jsonObject.getString("link_play_episode"));
                        episode.setTime(jsonObject.getString("time"));

                        episodeList.add(episode);
                        episodeAdapter = new EpisodeAdapter(context, episodeList);
                        recyclerView.setAdapter(episodeAdapter);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public void getRandom(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<AllInformation> list) {

        this.requestQueue = requestQueue;
        this.LINK = randomLink + url;
        this.randomList = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {


                JSONArray jsonArray = response.getJSONArray("movie_streaming");


                int random = (int) (Math.random() * jsonArray.length());

                if (random < 4)
                    random = random + 4;
                if (random >= jsonArray.length())
                    random = jsonArray.length() - 1;


                for (int i = random - 4; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AllInformation allInformation = new AllInformation();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");
                    String director = jsonObject.getString("director");
                    String rate_imdb = jsonObject.getString("rate_imdb");
                    String category_name = jsonObject.getString("catagory_name");
                    String rank = jsonObject.getString("rank");


                    allInformation.setId(id);
                    allInformation.setName(name);
                    allInformation.setLink_img(link_img);
                    allInformation.setTime(time);
                    allInformation.setPublished(published);
                    allInformation.setDirector(director);
                    allInformation.setRate_imdb(rate_imdb);
                    allInformation.setCategory_name(category_name);

                    randomList.add(allInformation);
                    randomAdapter = new RandomAdapter(randomList, context);
                    recyclerView.setAdapter(randomAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        requestQueue.add(request);
    }

    public void getRandomSeries(final Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<AllInformation> list) {

        this.requestQueue = requestQueue;
        this.LINK = randomLink + url;
        this.randomList = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null
                , response -> {
            try {


                JSONArray jsonArray = response.getJSONArray("movie_streaming");


                int random = (int) (Math.random() * jsonArray.length());

                if (random < 4)
                    random = random + 4;
                if (random >= jsonArray.length())
                    random = jsonArray.length() - 1;

                for (int i = random - 4; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AllInformation allInformation = new AllInformation();

                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String link_img = jsonObject.getString("link_img");
                    String time = jsonObject.getString("time");
                    String published = jsonObject.getString("published");
                    String director = jsonObject.getString("director");
                    String rate_imdb = jsonObject.getString("rate_imdb");
                    String category_name = jsonObject.getString("catagory_name");
                    String rank = jsonObject.getString("rank");


                    allInformation.setId(id);
                    allInformation.setName(name);
                    allInformation.setLink_img(link_img);
                    allInformation.setTime(time);
                    allInformation.setPublished(published);
                    allInformation.setDirector(director);
                    allInformation.setRate_imdb(rate_imdb);
                    allInformation.setCategory_name(category_name);

                    randomList.add(allInformation);
                    randomSeriesAdapter = new RandomSeriesAdapter(randomList, context);
                    recyclerView.setAdapter(randomSeriesAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        requestQueue.add(request);
    }

    public void getShowGenre(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<ShowGenre> list) {

        this.requestQueue = requestQueue;
        String LINK = linkShowGenre + url;
        this.showGenreList = list;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LINK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ShowGenre showGenre = new ShowGenre();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");
                        String genre = jsonObject.getString("genre_name");

                        showGenre.setId(id);
                        showGenre.setName(name);
                        showGenre.setLink_img(link_img);
                        showGenre.setTime(time);
                        showGenre.setPublished(published);
                        showGenre.setDirector(director);
                        showGenre.setRate_imdb(rate_imdb);
                        showGenre.setCategory_name(category_name);
                        showGenre.setGenre(genre);


                        showGenreList.add(showGenre);
                        showGenreAdapter = new ShowGenreAdapter(context, showGenreList);
                        recyclerView.setAdapter(showGenreAdapter);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(request);
    }

    public void getSearch(Context context, RequestQueue requestQueue, String url, RecyclerView recyclerView, List<AllInformation> list) {

        this.searchList = list;
        this.requestQueue = requestQueue;
        this.linkSearch = linkSearch + url;

        Log.e("Link", linkSearch);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, linkSearch, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AllInformation allInformation = new AllInformation();

                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String link_img = jsonObject.getString("link_img");
                        String time = jsonObject.getString("time");
                        String published = jsonObject.getString("published");
                        String director = jsonObject.getString("director");
                        String rate_imdb = jsonObject.getString("rate_imdb");
                        String category_name = jsonObject.getString("catagory_name");
                        String rank = jsonObject.getString("rank");


                        allInformation.setId(id);
                        allInformation.setName(name);
                        allInformation.setLink_img(link_img);
                        allInformation.setTime(time);
                        allInformation.setPublished(published);
                        allInformation.setDirector(director);
                        allInformation.setRate_imdb(rate_imdb);
                        allInformation.setCategory_name(category_name);

                        searchList.add(allInformation);
                        searchAdapter = new SearchAdapter(context, searchList);
                        recyclerView.setAdapter(searchAdapter);

                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }
}