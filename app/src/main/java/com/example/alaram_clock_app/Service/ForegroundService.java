package com.example.alaram_clock_app.Service;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alaram_clock_app.AlarmNotificationManager;
import com.example.alaram_clock_app.BroadCastReceiver.StopAlarmBroadCastReceiver;
import com.example.alaram_clock_app.R;
import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.RoomDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ForegroundService extends Service {

    private Ringtone ringtone;
    String tone;


    @Override
    public void onCreate() {
        super.onCreate();
        //tone = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM).toString();

//
//        List<Alarm_Details> alarmList;
//        RoomDB database;
//        database = RoomDB.getInstance(this);
//        alarmList = new ArrayList<>();
//        alarmList = database.mainDao().getALL();
//
//        for (Alarm_Details alarmDetails : alarmList) {
//            tone = alarmDetails.getRingtone_path();
//            //Log.e("DatabaseTOne", toneName);
//        }
//
//
//        Log.e("defaultTone", tone);
//        ringtone = RingtoneManager.getRingtone(getApplicationContext(), Uri.parse(tone));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        // Play the ringtone
//        ringtone.play();
//        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//      //  vibrator.vibrate(4000);
//
////        AlarmNotificationManager.showAlarmNotification(this,"Alarm","Time to WakeUp!");
//
//
//        Log.e("MyApp", "forgroundOnStartCommand");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyApp", "forgroundOnStartCommandOnDestroy");

      //  ringtone.stop();
//        vibrator.cancel();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
