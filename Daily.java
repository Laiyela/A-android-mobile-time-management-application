package com.example.selfdisciplinemaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.selfdisciplinemaster.Day1.activity_main_day1;
import com.example.selfdisciplinemaster.Day2.activity_main_day2;
import com.example.selfdisciplinemaster.Day3.activity_main_day3;
import com.example.selfdisciplinemaster.Day4.activity_main_day4;
import com.example.selfdisciplinemaster.Day5.activity_main_day5;
import com.example.selfdisciplinemaster.Day6.activity_main_day6;
import com.example.selfdisciplinemaster.Day7.activity_main_day7;

public class Daily extends AppCompatActivity {

    private Button btnday1, btnday2, btnday3, btnday4, btnday5, btnday6, btnday7, cancel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_daily);

        btnday1 = findViewById(R.id.btn_day1);
        btnday2 = findViewById(R.id.btn_day2);
        btnday3 = findViewById(R.id.btn_day3);
        btnday4 = findViewById(R.id.btn_day4);
        btnday5 = findViewById(R.id.btn_day5);
        btnday6 = findViewById(R.id.btn_day6);
        btnday7 = findViewById(R.id.btn_day7);
        cancel = findViewById(R.id.btn_cancel);

        btnday1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day1 = new Intent(Daily.this, activity_main_day1.class);
                startActivity(day1);
            }
        });

        btnday2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day2 = new Intent(Daily.this, activity_main_day2.class);
                startActivity(day2);
            }
        });
        btnday3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day3 = new Intent(Daily.this, activity_main_day3.class);
                startActivity(day3);
            }
        });
        btnday4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day4 = new Intent(Daily.this, activity_main_day4.class);
                startActivity(day4);
            }
        });
        btnday5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day5 = new Intent(Daily.this, activity_main_day5.class);
                startActivity(day5);
            }
        });
        btnday6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day6 = new Intent(Daily.this, activity_main_day6.class);
                startActivity(day6);
            }
        });
        btnday7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent day7 = new Intent(Daily.this, activity_main_day7.class);
                startActivity(day7);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Daily.this, HomeActivity.class);
                startActivity(back);
            }
        });
    }
}
