package com.example.realtime_production_data;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Dashboard extends AppCompatActivity {
    ImageButton btnML2;
    ImageButton btnML1;
    ImageButton brnGraph;
    ImageButton btnInventory;
    ImageButton btnMaintainance;
    ImageButton btnSupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button Logout;

        Logout= findViewById(R.id.btnLogout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent( Dashboard.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();


            }
        });



            btnML1=(ImageButton)findViewById(R.id.btn_ml1);
            btnML1.setClickable(true);
            btnML1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Dashboard.this,MachineLineOneData.class);
                    startActivity(intent);
                }
            });

            btnML2=(ImageButton)findViewById(R.id.btn_ml2);
            btnML2.setClickable(true);
            btnML2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2=new Intent(Dashboard.this,Machine_Line_Two_Data.class);
                    startActivity(intent2);

                }
            });

            brnGraph=(ImageButton) findViewById(R.id.btn_graph);
            brnGraph.setClickable(true);
            brnGraph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentforgraph =new Intent(Dashboard.this,graphicalrepresentation.class);
                    startActivity(intentforgraph);
                }

            });

           btnInventory=(ImageButton)findViewById(R.id.btn_inventory);
           btnInventory.setClickable(true);
           btnInventory.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent untentforinventory= new Intent(Dashboard.this,InventoryData.class);
                   startActivity(untentforinventory);
               }
           });

           btnMaintainance=findViewById(R.id.btn_maintainance);
           btnMaintainance.setClickable(true);
           btnMaintainance.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intentformaintainance=new Intent(Dashboard.this,MaintainanceData.class);
                   startActivity(intentformaintainance);
               }
           });



           btnSupport=findViewById(R.id.btn_support);
           btnSupport.setClickable(true);
           btnSupport.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intentforsupport =new Intent(Dashboard.this,Support.class);
                   startActivity(intentforsupport);
               }
           });
        Calendar calendar =Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView DateView=findViewById(R.id.tv_calender);
        DateView.setText(currentDate);

        }



    }
