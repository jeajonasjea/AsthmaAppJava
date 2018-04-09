package com.example.styrm.asthmaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jea10 on 4/6/2018.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
     //   String state = intent.getExtras().getString("extra");
       // Log.e("MyActivity", "In the receiver with " + state);

        //Intent serviceIntent = new Intent(context,RingtonePlayingService.class);
        //serviceIntent.putExtra("extra", state);

        //context.startService(serviceIntent);
    }
}
