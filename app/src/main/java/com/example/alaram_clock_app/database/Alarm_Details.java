package com.example.alaram_clock_app.database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.alaram_clock_app.BroadCastReceiver.StopAlarmBroadCastReceiver;
import com.example.alaram_clock_app.R;
import com.example.alaram_clock_app.Service.ForegroundService;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

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
    public void scheduleAlarm(Context context) {

//        Intent foregroundServiceIntent = new Intent(context.getApplicationContext()
//                , ForegroundService.class);
//        context.getApplicationContext().startService(foregroundServiceIntent);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        // Create an Intent for the alarm action
//        Intent alarmIntent = new Intent(context, StopAlarmBroadCastReceiver.class);
//        // ... set any extras or data in the Intent as needed
//
//        // Create a PendingIntent for the alarm action
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID, alarmIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//
//        // Calculate the alarm time using hour and minute properties
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//        calendar.set(Calendar.SECOND, 0);
//
//        // Use AlarmManager to schedule the alarm
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        } else {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        }
//    }
//    public void schedule(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
//        Bundle bundle=new Bundle();
//        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),this);
//        intent.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        // if alarm time has already passed, increment day by 1
//        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
//        }
//
//        if (!getIsEnabled()) {
//            String toastText = null;
//            try {
//                toastText = String.format(Locale.US,"One Time Alarm %s scheduled at %02d:%02d", title,  hour, minute);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
//
//            alarmManager.setExact(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.getTimeInMillis(),
//                    alarmPendingIntent
//            );
//        } else {
//            String toastText = String.format(Locale.US,"Recurring Alarm %s scheduled  at %02d:%02d", title, hour, minute);
//            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
//
//            final long RUN_DAILY = 24 * 60 * 60 * 1000;
//            alarmManager.setRepeating(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.getTimeInMillis(),
//                    RUN_DAILY,
//                    alarmPendingIntent
//            );
//        }
//
//         setIsEnabled(true);
//    }
//
//    public void cancelAlarm(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        alarmManager.cancel(alarmPendingIntent);
//        setIsEnabled(false);
//        String toastText = String.format(Locale.US,"Alarm cancelled for %02d:%02d", hour, minute);
//        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
//        Log.i("cancel", toastText);
//    }
//

    }
}
