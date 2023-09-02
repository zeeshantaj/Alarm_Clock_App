package com.example.alaram_clock_app.Models;

public class Alarm_Time {
    private int hours;
    private int minute;

    public Alarm_Time(){

    }
    public Alarm_Time(int hours, int minute) {
        this.hours = hours;
        this.minute = minute;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
