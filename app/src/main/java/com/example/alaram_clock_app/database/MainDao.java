package com.example.alaram_clock_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(Alarm_Details alarm_data);

    @Query("SELECT * FROM alarm_data ORDER BY id DESC")
    List<Alarm_Details> getALL();
//    @Query("UPDATE alarm_data SET enabled = :enabled WHERE ID = :id")
//    void isEnabled(int id,boolean enabled);


    @Delete
    void delete(Alarm_Details alarm_data);

    @Query("UPDATE alarm_data SET Enabled = :Enable WHERE ID = :id")
    void Enabled(int id, boolean Enable);
}
