package com.example.controlcenter;

import android.app.Application;

import androidx.room.Room;

import com.example.controlcenter.database.ControlDB;

public class App extends Application {
    private static App instance;
    private Storage storage;
    private ControlDB controlDB;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDB();
        storage = new Storage();
    }

    public static App getInstance() {
        return instance;
    }

    public ControlDB getControlDB() {
        return controlDB;
    }

    private void initDB() {
        controlDB = Room.databaseBuilder(getApplicationContext(), ControlDB.class, "Apps")
                .allowMainThreadQueries().build();
    }

    public Storage getStorage() {
        return storage;
    }
}
