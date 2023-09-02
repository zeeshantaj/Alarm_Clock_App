package com.example.alaram_clock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alaram_clock_app.Adapter.AlarmAdapter;

import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.RoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addAlarmBtn;
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;
    private RoomDB database;
    List<Alarm_Details> alarmList;
    private TextView currentTimeTxt;
    private Handler handler;
    private Runnable updateTimeRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addAlarmBtn = findViewById(R.id.addAlarmBtn);
        recyclerView = findViewById(R.id.set_alarmRecyclerView);
        currentTimeTxt = findViewById(R.id.currentDateTimeText);
        database = RoomDB.getInstance(this);

        alarmList = new ArrayList<>();
        alarmList = database.mainDao().getALL();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new AlarmAdapter(this, alarmList, new AlarmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Alarm_Details item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("ACTION REQUIRE")
                        .setMessage("Do you want to Delete Or Edit This Alarm Data")
                        .setPositiveButton("Edit Task", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Delete Task", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                database.mainDao().delete(item);
                                Toast.makeText(MainActivity.this, "Alarm Deleted", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                                alarmList.remove(item);
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .show();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addAlarmBtn.setOnClickListener(view -> {
            startActivity(new Intent(this,Set_Alarm_Activity.class));
        });

        handler = new Handler();
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                setCurrentTime();
                handler.postDelayed(this, 1000);
            }
        };

    }
    private void setCurrentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy \n hh:mm:ss a");
        String formattedDate = df.format(c.getTime());
        currentTimeTxt.setText(formattedDate);
    }


    @Override
    protected void onStart() {
        super.onStart();
        handler.post(updateTimeRunnable); // Start updating the time

  }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimeRunnable); // Stop updating the time
    }
}