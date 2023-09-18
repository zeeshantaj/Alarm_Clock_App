package com.example.alaram_clock_app.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.alaram_clock_app.BroadCastReceiver.StopAlarmBroadCastReceiver;
import com.example.alaram_clock_app.R;
import com.example.alaram_clock_app.database.Alarm_Details;

public class AlarmService extends Service {
    public static final String ACTION_START_ALARM = "START_ALARM";
    public static final String ACTION_STOP_ALARM = "STOP_ALARM";
    public static final int NotificationID =123;
    public static Ringtone ringtone;
    public static boolean isForeground = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            String action = intent.getAction();
            Log.e("MyApp", "AlarmService received action: " + action);
            if (ACTION_START_ALARM.equals(action)) {
                startRingtone(getApplicationContext());
                isForeground = true;
            }
            if (ACTION_STOP_ALARM.equals(action)) {
                Log.e("MyApp", "AlarmService received action: " + action);
                stopRingtone(getApplicationContext());
                stopForeground(true);
                isForeground = false;
            }
        }



        return START_STICKY;
    }
    private void stopRingtone(Context context) {
        // Stop the ringtone
        Log.e("MyApp", "stopRingtone called");
        String tone = RingtoneManager.getActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_ALARM).toString();

        ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), Uri.parse(tone));

        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
            Log.e("MyApp", "Ringing Stopped");
        }


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NotificationID);
        Log.e("MyApp", "Notification Cancelled");
    }

    private void startRingtone(Context context) {
        // Get the AudioManager
        Alarm_Details alarmDetails = new Alarm_Details();
        alarmDetails.getRingtone_path();


        String tone = RingtoneManager.getActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_ALARM).toString();

        ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), Uri.parse(tone));

        if (ringtone != null) {
            ringtone.play();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent stopIntent = new Intent(context, ServiceBroadcast.class);
        stopIntent.setAction("STOP_ALARM"); // Use a unique action string

        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm_channel")
                .setContentTitle("Alarm")
                .setContentText("Alarm is ringing!")
                .setSmallIcon(R.drawable.baseline_alarm_on_24)
                .addAction(R.drawable.baseline_alarm_off_24, "Stop", stopPendingIntent) // Add a "Stop" action
                .setAutoCancel(true);
                Log.e("MyApp", "stopCLicked"+stopIntent.getAction());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("alarm_channel", "Alarm", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(NotificationID, builder.build()); // Use a unique notification ID
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
