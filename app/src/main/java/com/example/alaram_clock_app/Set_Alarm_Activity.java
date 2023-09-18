package com.example.alaram_clock_app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alaram_clock_app.BroadCastReceiver.StopAlarmBroadCastReceiver;
import com.example.alaram_clock_app.Models.Alarm_Time;
import com.example.alaram_clock_app.Service.AlarmService;
import com.example.alaram_clock_app.Service.ServiceBroadcast;
import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.MainDao;
import com.example.alaram_clock_app.database.RoomDB;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Set_Alarm_Activity extends AppCompatActivity {


    private Button setBtn;
    private CardView ringtoneCard, vibrateCard;
    private TimePicker timePicker;
    private CheckBox vibrateCheckBox;

    private boolean isChecked = false;

    private RoomDB database;
    private String tone;
    private Ringtone ringtone;

    private TextView ringToneName;
    private Alarm_Details alarm;
    private CardView selectRingTone;

    BroadcastReceiver stopAlarmReceiver = new StopAlarmBroadCastReceiver();
    private ActivityResultLauncher<String> filePickerLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        timePicker = findViewById(R.id.timerPicker);
        setBtn = findViewById(R.id.setBtn);
        ringToneName = findViewById(R.id.ringtoneName);
        ringtoneCard = findViewById(R.id.select_ringtone_card);
        vibrateCard = findViewById(R.id.vibrateCard);

        vibrateCheckBox = findViewById(R.id.vibrateCheckBox);
        tone = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM).toString();
        ringtone = RingtoneManager.getRingtone(this, Uri.parse(tone));
        ringToneName.setText(ringtone.getTitle(this));

        alarm = new Alarm_Details();
        database = RoomDB.getInstance(this);


        vibrateCard.setOnClickListener(view -> {
            if (!isChecked) {
                vibrateCheckBox.setChecked(true);
                isChecked = true;
            } else if (isChecked) {
                vibrateCheckBox.setChecked(false);
                isChecked = false;
            }
            Toast.makeText(this, "" + isChecked, Toast.LENGTH_SHORT).show();

        });

        ringtoneCard.setOnClickListener(view -> {

            filePickerLauncher.launch("audio/mpeg");

        });

        setBtn.setOnClickListener(view -> {

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "pm";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "am";
            } else if (hour == 12) {
                timeSet = "pm";
            } else {
                timeSet = "am";
            }


            String formatted1 = String.format(Locale.US, "%02d:%02d:%s", hour, minute, timeSet);
            Toast.makeText(this, "Alarm Set SuccessFull" + formatted1, Toast.LENGTH_SHORT).show();

            Log.e("MyApp", formatted1);
            alarm.setTime(formatted1);
            alarm.setIsEnabled(true);
            alarm.setRingtone_path(tone);
            alarm.setVibrate(isChecked);
            alarm.setTitle("My Alarm");
            alarm.setHour(hour);
            alarm.setMinute(minute);
            System.out.println("details" + alarm.getHour() + alarm.getID() + alarm.getMinute() + alarm.getIsEnabled() + alarm.getRingtone_path() + alarm.getTitle());
            database.mainDao().insert(alarm);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            calendar.set(Calendar.SECOND, 0);


            Intent alarmIntent = new Intent(this, ServiceBroadcast.class);

            alarmIntent.setAction("START_ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Set_Alarm_Activity.ALARM_SERVICE);


            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);



            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+20000,pendingIntent);


            //startService(alarmIntent);


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        // Handle the selected file URI
                        handleSelectedFile(uri);
                    }
                }
        );

    }




    private void handleSelectedFile(Uri fileUri) {
        // Use the fileUri as needed
        // For example, display the file name to the user
        //tone = getFileNameFromUri(fileUri);
        tone = fileUri.toString();


        if (!tone.isEmpty()){
            ringtone = RingtoneManager.getRingtone(this, Uri.parse(tone));
            ringToneName.setText(ringtone.getTitle(this));
           // alarm.setRingtone_path(fileName);
        }

        Intent intent = new Intent();
        intent.putExtra("ringtoneUri", tone);

        Log.e("MyApp", "tone" + fileUri);
    }
    private String getFileNameFromUri(Uri uri) {
        String displayName = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex != -1) {
                displayName = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return displayName;
    }
}