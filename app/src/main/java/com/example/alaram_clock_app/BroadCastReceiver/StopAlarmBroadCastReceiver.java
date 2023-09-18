package com.example.alaram_clock_app.BroadCastReceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.example.alaram_clock_app.R;


public class StopAlarmBroadCastReceiver extends BroadcastReceiver {

    private Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {

        String tone = RingtoneManager.getActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_ALARM).toString();

        ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), Uri.parse(tone));

        if ("PLAY_ALARM".equals(intent.getAction())) {
            Log.e("MyApp", "ALARMSTART");
            playRingtone(context.getApplicationContext());
            }
        else if ("STOP_ALARM".equals(intent.getAction())){
            Log.e("MyApp", "ALARMSTOP");
            stopRinging(context.getApplicationContext());

        }


        Log.e("MyApp", "onReceive");
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
    }

}
