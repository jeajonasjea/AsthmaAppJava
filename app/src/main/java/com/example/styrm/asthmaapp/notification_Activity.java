package com.example.styrm.asthmaapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class notification_Activity extends AppCompatActivity {
    AlarmManager alarm_manager;
    TimePicker time_picker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_);
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        time_picker = (TimePicker)findViewById(R.id.timePicker);

        update_text = (TextView) findViewById(R.id.updateText);


        final Calendar calender = Calendar.getInstance();

        final Intent My_intent = new Intent(this.context, Alarm_Receiver.class);

        Button alarm_on = (Button) findViewById(R.id.alarmOn);

        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // setting calender to hour and minute we picked
                    calender.set(Calendar.HOUR_OF_DAY, time_picker.getHour());
                    calender.set(Calendar.MINUTE, time_picker.getMinute());

                    // get string values
                int hour = time_picker.getHour();
                int minute = time_picker.getMinute();

                // convert int to string

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (minute < 10){
                    minute_string = "0" + String.valueOf(minute);
                }

                    set_alarm_text("alarm set to: " + hour_string +":" + minute_string);

                        pending_intent = PendingIntent.getBroadcast(notification_Activity.this,0, My_intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        alarm_manager.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pending_intent);
            }
        });

        Button alarm_off = (Button)findViewById(R.id.alarmOff);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("alarm off");
                alarm_manager.cancel(pending_intent);
            }
        });

    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }
}
