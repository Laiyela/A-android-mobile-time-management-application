package com.example.selfdisciplinemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.selfdisciplinemaster.Day1.note_main_day1;
import com.example.selfdisciplinemaster.Day2.note_main_day2;
import com.example.selfdisciplinemaster.Day3.note_main_day3;
import com.example.selfdisciplinemaster.Day4.note_main_day4;
import com.example.selfdisciplinemaster.Day5.note_main_day5;
import com.example.selfdisciplinemaster.Day6.note_main_day6;
import com.example.selfdisciplinemaster.Day7.note_main_day7;

public class NoteActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        btn1 = findViewById(R.id.btn_note_day1);
        btn2 = findViewById(R.id.btn_note_day2);
        btn3 = findViewById(R.id.btn_note_day3);
        btn4 = findViewById(R.id.btn_note_day4);
        btn5 = findViewById(R.id.btn_note_day5);
        btn6 = findViewById(R.id.btn_note_day6);
        btn7 = findViewById(R.id.btn_note_day7);
        btnCancel = findViewById(R.id.btn_note_cancel);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn1 = new Intent(NoteActivity.this, note_main_day1.class);
                startActivity(btn1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn2 = new Intent(NoteActivity.this, note_main_day2.class);
                startActivity(btn2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn3 = new Intent(NoteActivity.this, note_main_day3.class);
                startActivity(btn3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn4 = new Intent(NoteActivity.this, note_main_day4.class);
                startActivity(btn4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn5 = new Intent(NoteActivity.this, note_main_day5.class);
                startActivity(btn5);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn6 = new Intent(NoteActivity.this, note_main_day6.class);
                startActivity(btn6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn7 = new Intent(NoteActivity.this, note_main_day7.class);
                startActivity(btn7);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnCancel= new Intent(NoteActivity.this, HomeActivity.class);
                startActivity(btnCancel);
            }
        });

    }
}