package com.example.styrm.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Spoergeskema extends AppCompatActivity {

    String svarGodt = "";
    String svarDårligt = "";
    CheckBox boxGodt = findViewById(R.id.godtBox);
    CheckBox boxDårligt = findViewById(R.id.dårligBox);
    String svar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoergeskema);

        Button button;


        button = (Button) findViewById(R.id.svarKnap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FileOutputStream fOut;

                try {
                    ReadCheckBox();

                }catch (WriteToFileExeption e){
                    System.out.println(e.getMessage());
                }

                svar = svarGodt + "," + svarDårligt + ",";

                try {
                    fOut = openFileOutput("Spørgeskema.txt", MODE_PRIVATE);

                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                }catch (FileNotFoundException e){
                    System.out.print(e.getMessage());
                }
            }
        });
    }
    public void ReadCheckBox() throws WriteToFileExeption{
        if (boxGodt.isSelected() && !boxDårligt.isSelected()){
            svarGodt = "true";
            svarDårligt = "false";
        } else if (boxDårligt.isSelected() && !boxGodt.isSelected()) {
            svarGodt = "false";
            svarDårligt = "true";
        }else{
            throw new WriteToFileExeption("Could not read from checkbox");
        }
    }
}

