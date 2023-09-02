package com.example.alaram_clock_app;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alaram_clock_app.BroadCastReceiver.StopAlarmBroadCastReceiver;
import com.example.alaram_clock_app.Service.ForegroundService;
import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.RoomDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmNotificationManager {
    public static final int NOTIFICATION_ID = 1;

    public static final String CHANNEL_ID = "Alarm Channel";
    public static void showAlarmNotification(Context context,String title,String message) {
        // Create a notification channel (if not already created)
//        try {
//            // Create a notification with a stop action
//            Intent stopIntent = new Intent(context, StopAlarmBroadCastReceiver.class);
//            PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent,
//                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle(title)
//                    .setContentText(message)
//                    .setSmallIcon(R.drawable.baseline_alarm_on_24)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setOngoing(true) // Notification cannot be dismissed
//                    .addAction(R.drawable.baseline_alarm_off_24, "Stop", stopPendingIntent);
//            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notificationBuilder.build());
//
//        }
//        catch (SecurityException e){
//            e.printStackTrace();
//        }
//        createNotificationChannel(context, CHANNEL_ID, "Alarm Channel", NotificationManager.IMPORTANCE_HIGH);
//    }
//    public static void createNotificationChannel(Context context, String channelId, String channelName, int importance) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
//            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
    }

}
