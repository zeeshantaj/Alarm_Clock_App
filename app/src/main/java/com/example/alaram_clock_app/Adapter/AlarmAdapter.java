package com.example.alaram_clock_app.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alaram_clock_app.R;
import com.example.alaram_clock_app.database.Alarm_Details;
import com.example.alaram_clock_app.database.RoomDB;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private Context context;
    private List<Alarm_Details> alarm_timeList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Alarm_Details item);
    }

    public AlarmAdapter(Context context, List<Alarm_Details> alarm_timeList,OnItemClickListener itemClickListener) {
        this.context = context;
        this.alarm_timeList = alarm_timeList;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setted_alarm_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.ViewHolder holder, int position) {

        Alarm_Details alarmTime = alarm_timeList.get(position);
        holder.hours.setText(alarmTime.getTime());


        holder.enabledAlarm.setOnCheckedChangeListener(null);
        holder.enabledAlarm.setChecked(alarmTime.getIsEnabled());


        holder.enabledAlarm.setOnCheckedChangeListener((buttonView,isChecked)->{
            alarmTime.setIsEnabled(isChecked);

            RoomDB database;
            database = RoomDB.getInstance(context.getApplicationContext());

            database.mainDao().Enabled(alarmTime.getID(),isChecked);
            if (!isChecked){
                Toast.makeText(context, "Alarm Dismiss", Toast.LENGTH_SHORT).show();
            }
            if (isChecked){
                Toast.makeText(context, "Alarm Enabled", Toast.LENGTH_SHORT).show();


            }
        });
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(alarmTime);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarm_timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hours;
        private SwitchMaterial enabledAlarm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hours = itemView.findViewById(R.id.alarmHours);
            enabledAlarm = itemView.findViewById(R.id.enabledAlarm);

        }
    }
}
