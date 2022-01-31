package com.example.realtime_production_data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Machine_Line_Two_Data extends AppCompatActivity {
    DatabaseReference mreff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine__line__two__data);
        Button btn2;
        final TextView Count2,Height2,Accepted2,Rejected2,TotalProduction2;
        Count2=findViewById(R.id.tv_Count2);
        Height2=findViewById(R.id.tv_Height2);
        Accepted2=findViewById(R.id.tv_Accepted2);
        Rejected2=findViewById(R.id.tv_Rejected2);
        TotalProduction2=findViewById(R.id.tv_TotalProduction2);

        btn2=findViewById(R.id.btn_Show2);
        DatabaseReference finalReff = mreff;

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mreff= FirebaseDatabase.getInstance().getReference().child("ML2");
            mreff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String count2=dataSnapshot.child("Count").getValue().toString();
                    String height2=dataSnapshot.child("Height").getValue().toString();
                    String accepted2=dataSnapshot.child("Accepted").getValue().toString();
                    String rejected2=dataSnapshot.child("Rejected").getValue().toString();
                    String totalp2=dataSnapshot.child("Total Production").getValue().toString();
                    Count2.setText(count2);
                    Height2.setText(height2);
                    Accepted2.setText(accepted2);
                    Rejected2.setText(rejected2);
                    TotalProduction2.setText(totalp2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            }
        });

    }
}