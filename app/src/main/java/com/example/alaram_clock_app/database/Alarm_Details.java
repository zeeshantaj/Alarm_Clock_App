package com.example.alaram_clock_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity(tableName = "Alarm_data")
public class Alarm_Details implements Serializable {

    @PrimaryKey(autoGenerate = true)
        int ID = 0;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "hour")
    int hour;
    @ColumnInfo(name = "minute")
    int minute;
    @ColumnInfo(name = "ringtone_path")
    String ringtone_path;
    @ColumnInfo(name = "vibrate")
    boolean vibrate;

    @ColumnInfo(name = "day_of_week")
    String day_of_month;
    @ColumnInfo(name = "Enabled")
    boolean Enabled;
    @ColumnInfo(name = "Title")
    String title;
    @ColumnInfo(name = "AlarmID")
    String alarmId;

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRingtone_path() {
        return ringtone_path;
    }

    public void setRingtone_path(String ringtone_path) {
        this.ringtone_path = ringtone_path;
    }

    public boolean getVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }


    public String getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(String day_of_month) {
        this.day_of_month = day_of_month;
    }

    public boolean getIsEnabled() {
        return Enabled;
    }

    public void setIsEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }
}
