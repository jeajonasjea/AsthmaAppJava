package com.example.styrm.asthmaapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Spoergeskema extends AppCompatActivity {

    private String svarGodt = "";
    private String svarDårligt = "";
    private CheckBox boxGodt = findViewById(R.id.godtBox);
    private CheckBox boxDårligt = findViewById(R.id.dårligBox);
    private String svar;
    private String result;
    public LocationManager locationManager;
    public LocationListener locationListener;
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoergeskema);

        Button button;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        button = (Button) findViewById(R.id.svarKnap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fOut;

                try {
                    ReadCheckBox();

                } catch (WriteToFileExeption e) {
                    System.out.println(e.getMessage());
                }
                GetLocation();

                svar = svarGodt + "," + svarDårligt + ",";

                try {
                    fOut = openFileOutput("Spørgeskema.txt", MODE_PRIVATE);

                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                } catch (FileNotFoundException e) {
                    System.out.print(e.getMessage());
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void GetLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //for hver gang requestPermissions bliver instantieret bliver der lavet et tilbagekald som gør at metoden onRequestPermissionsResult bliver kaldet.
            requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET},
                    10);
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double x = location.getAltitude();
                    double v = location.getLongitude();
                    result = String.valueOf(x) + ";" + String.valueOf(v);

                } else {
                    throw new WriteToFileExeption("Could not read Location");
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    GetLocation();
                }
        }
    }

    public void ReadCheckBox() throws WriteToFileExeption {
        if (boxGodt.isSelected() && !boxDårligt.isSelected()) {
            svarGodt = "true";
            svarDårligt = "false";
        } else if (boxDårligt.isSelected() && !boxGodt.isSelected()) {
            svarGodt = "false";
            svarDårligt = "true";
        } else {
            throw new WriteToFileExeption("Could not read from checkbox");
        }
    }
/*
    public void GetLocation() {
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                result = location.getLongitude() + " " + location.getAltitude() + ",";

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            }
        };


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 10:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    GetGpsLocation();
                }
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void GetGpsLocation(){
        //For at kunne køre metoden "locationManager.requestLocationUpdates"
        //krævede det et check for at sikre at man har tilledelse til at bruge gps'en.
        //hvilket vi bruger if statementet nedenunder til.
        //og for at kunne få lov til det, krævede det at vi lavede et kald på api level 23.
        //og eftersom vi står til at lave til api level 15
        //skulle metoden specificeres med @TargetApi(Build.VERSION_CODES.M)
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //for hver gang requestPermissions bliver instantieret bliver der lavet et tilbagekald som gør at metoden onRequestPermissionsResult bliver kaldet.
            requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET},
                    10);
            return;
        }
        locationManager.requestLocationUpdates("gps", 600000, 20000, locationListener);
    }
    */
}


