package com.example.styrm.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.FileOutputStream;

public class Spoergeskema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoergeskema);

        Button button;

        button = (Button) findViewById(R.id.svarKnap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String svarGodt = "";
                String svarDårligt = "";
                CheckBox boxGodt = findViewById(R.id.godtBox);
                CheckBox boxDårligt = findViewById(R.id.dårligBox);
                String svar;

                if(boxGodt.isSelected() == true){
                    svarGodt = "true";
                    svarDårligt = "false";
                }
                else if(boxDårligt.isSelected() == true){
                    svarGodt = "false";
                    svarDårligt = "true";
                }

                 svar = svarGodt + "," + svarDårligt + ",";

                String filename = "myFile";
                FileOutputStream outputstream;

                try{
                    outputstream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputstream.write(svar.getBytes());

                }catch (Exception e) {
                e.printStackTrace();
                }
                Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMain);
            }
        });

    }

}