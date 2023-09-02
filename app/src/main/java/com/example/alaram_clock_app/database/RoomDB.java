package com.example.alaram_clock_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Alarm_Details.class,version = 2,exportSchema = false)
public abstract class RoomDB extends RoomDatabase{

    private static RoomDB database;
    public static String DATABASE_NAME = "Alarm_Database";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDao mainDao();
}
