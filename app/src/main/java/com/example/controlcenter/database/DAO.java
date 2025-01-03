package com.example.controlcenter.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controlcenter.App;
import com.example.controlcenter.model.Apps;

import java.util.List;

@Dao
public interface DAO {
    String query = "Select * from Apps";

    @Query(query)
    List<Apps> getAllApp();

    @Query("Select * from Apps where use = 1")
    List<Apps> getUseApp();

    @Query("Select * from Apps where use = 0")
    List<Apps> getNonUseApp();

    @Insert
    void insertApps(Apps apps);

    @Update
    void updateApps(Apps apps);
    @Query("SELECT COUNT(*) \n" +
            "FROM Apps \n" +
            "WHERE appName =:appN")
    int check(String appN);
}
