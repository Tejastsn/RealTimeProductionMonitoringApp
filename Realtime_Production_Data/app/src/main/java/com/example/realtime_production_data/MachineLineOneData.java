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

public class MachineLineOneData extends AppCompatActivity {
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_line_one_data);
        Button btn1;
        final TextView Count,Height,Accepted,Rejected,TotalProduction;
        Count=findViewById(R.id.tv_Count1);
        Height=findViewById(R.id.tv_Height1);
        Accepted=findViewById(R.id.tv_Accepted1);
        Rejected=findViewById(R.id.tv_Rejected1);
        TotalProduction=findViewById(R.id.tv_TotalProduction1);

        btn1=findViewById(R.id.btn_Show1);
        DatabaseReference finalReff = reff;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("ML1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String count=dataSnapshot.child("Count").getValue().toString();
                        String height=dataSnapshot.child("Height").getValue().toString();
                        String accepted=dataSnapshot.child("Accepted").getValue().toString();
                        String rejected=dataSnapshot.child("Rejected").getValue().toString();
                        String totalp=dataSnapshot.child("Total Production").getValue().toString();
                        Count.setText(count);
                        Height.setText(height);
                        Accepted.setText(accepted);
                        Rejected.setText(rejected);
                        TotalProduction.setText(totalp);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}