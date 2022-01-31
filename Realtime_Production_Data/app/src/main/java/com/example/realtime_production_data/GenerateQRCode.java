package com.example.realtime_production_data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateQRCode extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_CODE=100;


    private Button Camera;
    private Button generate;
private Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_q_r_code);

        //btn id generation for camera
        Camera=findViewById(R.id.camera);
        generate=findViewById(R.id.button_generate);
        scan=findViewById(R.id.scan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GenerateQRCode.this,ScanActivity.class);
                startActivity(intent);
            }
        });




        // creating listener
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);
            }
        });
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentforgen=new Intent(GenerateQRCode.this,GenerateCode.class);
                        startActivity(intentforgen);
            }
        });


    }
    public void checkPermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(GenerateQRCode.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(GenerateQRCode.this, new String[]{permission},requestCode);}
        else{
            Toast.makeText(GenerateQRCode.this,"Permission Already Granted",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode==CAMERA_PERMISSION_CODE){
    if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
        Toast.makeText(GenerateQRCode.this,"Permission Granted",Toast.LENGTH_SHORT).show();

    }
    else{
        Toast.makeText(GenerateQRCode.this,"Permission Denied",Toast.LENGTH_SHORT).show();
    }
        }
    }



}