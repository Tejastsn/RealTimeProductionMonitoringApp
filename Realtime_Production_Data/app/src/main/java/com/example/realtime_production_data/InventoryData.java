package com.example.realtime_production_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InventoryData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_data);
        Button GenerateQR;
        GenerateQR=findViewById(R.id.button_generateQR);
        GenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentforgenerate=new Intent(InventoryData.this,GenerateQRCode.class);
              startActivity(intentforgenerate);

            }
        });
    }
}