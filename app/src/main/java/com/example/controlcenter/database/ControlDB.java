package com.example.controlcenter.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.controlcenter.model.Apps;

@Database(entities = {Apps.class}, version = 1)
public abstract class ControlDB extends RoomDatabase {
    public abstract DAO getDao();
}
