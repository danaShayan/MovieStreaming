package com.example.moviestreaming.Database.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviestreaming.Database.ModelDB.Favorite;


@Database(entities = {Favorite.class}, version = 1)
public abstract class RoomDatabaseApp extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    public static RoomDatabaseApp instance;

    public static RoomDatabaseApp getInstance(Context context) {

        if (instance == null)
            instance = Room.databaseBuilder(context, RoomDatabaseApp.class, "MovieStreaming Room")
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }


}
