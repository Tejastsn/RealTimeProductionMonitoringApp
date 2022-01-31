package com.example.realtime_production_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class graphicalrepresentation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphicalrepresentation);

        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry>visitors =new ArrayList<>();

        visitors.add(new BarEntry(1,100));
        visitors.add(new BarEntry(2,90));
        visitors.add(new BarEntry(3,115));
        visitors.add(new BarEntry(4,125));
        visitors.add(new BarEntry(5,20));
        visitors.add(new BarEntry(7,111));
        visitors.add(new BarEntry(8,90));
        visitors.add(new BarEntry(9,92));

        BarDataSet barDataSet =new BarDataSet(visitors,"PRODUCTION DATA FOR MONTH OF DECEMBER");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(android.R.color.black);
        barDataSet.setValueTextSize(16f);

        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("DAY WISE TOTAL PRODUCTION");
        barChart.animateY(2000);

    }
}