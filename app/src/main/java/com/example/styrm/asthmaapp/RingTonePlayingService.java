package com.example.styrm.asthmaapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.security.Provider;

/**
 * Created by jea10 on 4/11/2018.
 */

public class RingTonePlayingService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public int onStartCommand(Intent intent) {

        Log.e("in the service", "StartCommand");

        Intent service_intent = new Intent(Context, RingTonePlayingService.class );

        context.StartService

        // return START_NOT_STICKY;
    }
}
