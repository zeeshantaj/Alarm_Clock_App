package com.example.alaram_clock_app.Service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.alaram_clock_app.R;

public class AlarmService extends Service {

    private WindowManager windowManager;
    private View dismissView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dismissView != null) {
            windowManager.removeView(dismissView);
        }
    }
}

