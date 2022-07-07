package com.example.moviestreaming.Database.ModelDB;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favorite")
public class Favorite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "link_img")
    public String link_img;

    @ColumnInfo(name = "director")
    public String director;

    @ColumnInfo(name = "rate_imdb")
    public String rate_imdb;

    @ColumnInfo(name = "published")
    public String published;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "category_name")
    public String category_name;

    @ColumnInfo(name = "genre_name")
    public String genre_name;


}
