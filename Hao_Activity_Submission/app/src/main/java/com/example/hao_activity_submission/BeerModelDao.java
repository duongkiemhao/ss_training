package com.example.hao_activity_submission;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import androidx.room.Query;

@Dao
public interface BeerModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<BeerModelRoom> beers);

    @Query("SELECT * FROM Beer Where id between :value1 and :value2")
    LiveData<List<BeerModelRoom>> gettingAllBeer(int value1, int value2);

    @Query("DELETE FROM Beer")
    void deleteAll();
}
