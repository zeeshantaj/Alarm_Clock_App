package com.example.alaram_clock_app.BroadCastReceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alaram_clock_app.AlarmNotificationManager;
import com.example.alaram_clock_app.R;
import com.example.alaram_clock_app.Service.AlarmService;
import com.example.alaram_clock_app.Service.ForegroundService;
import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.RoomDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StopAlarmBroadCastReceiver extends BroadcastReceiver {

    private Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {


        if ("PLAY_ALARM".equals(intent.getAction())) {
            playRingtone(context.getApplicationContext());
        } else if ("STOP_ALARM".equals(intent.getAction())){
            stopRinging(context.getApplicationContext());
        }

      //  playRingtone(context.getApplicationContext());

        Log.e("MyApp", "onReceive");
      //  showDismissView(context.getApplicationContext());
    }

    private void stopRinging(Context context) {
        // Stop the ringtone
        if (ringtone != null && ringtone.isPlaying()) {

            ringtone.stop();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(123);
    }

    private void playRingtone(Context context) {
        // Get the AudioManager
        String tone = RingtoneManager.getActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_ALARM).toString();

        ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), Uri.parse(tone));

        if (ringtone != null) {
            ringtone.play();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent stopIntent = new Intent(context, StopAlarmBroadCastReceiver.class);
        stopIntent.setAction("STOP_ALARM"); // Use a unique action string

        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm_channel")
                .setContentTitle("Alarm")
                .setContentText("Alarm is ringing!")
                .setSmallIcon(R.drawable.baseline_alarm_on_24)
                .addAction(R.drawable.baseline_alarm_off_24, "Stop", stopPendingIntent) // Add a "Stop" action
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("alarm_channel", "Alarm", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(123, builder.build()); // Use a unique notification ID

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        // Set the ringer mode to normal (in case it's in silent or vibrate mode)
//        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        // Increase the volume to the maximum level
//        audioManager.setStreamVolume(AudioManager.STREAM_RING, audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
//        Intent foregroundServiceIntent = new Intent(context.getApplicationContext(), ForegroundService.class);
//        context.getApplicationContext().startService(foregroundServiceIntent);
    }

}
