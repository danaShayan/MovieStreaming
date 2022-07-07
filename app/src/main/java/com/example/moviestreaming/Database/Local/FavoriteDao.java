package com.example.moviestreaming.Database.Local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviestreaming.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    Flowable<List<Favorite>> getAllFavoriteItem();

    @Insert
    void insertFavoriteItem(Favorite... favorites);

    @Delete
    void deleteFavoriteItem(Favorite favorite);

    @Query("SELECT EXISTS(SELECT 1 FROM FAVORITE WHERE id=:itemFavID)")
    int isFavorite(int itemFavID);






}
