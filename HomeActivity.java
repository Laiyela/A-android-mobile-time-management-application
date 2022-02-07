package com.example.selfdisciplinemaster;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.selfdisciplinemaster.Month.activity_main_month;
import com.example.selfdisciplinemaster.Week.activity_main_week;

import com.example.selfdisciplinemaster.Year.activity_main_year;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
  //  private TextView tvYear, tvMonth, tvWeek;
    private CalendarView calendarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       calendarview = findViewById(R.id.calendarView);

        if (calendarview != null) {
            calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    String msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year;
                    Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }



        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_Note:
                Intent note = new Intent(HomeActivity.this, NoteActivity.class);
                startActivity(note);
                Toast.makeText(this, "Note page", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_daily:
                Intent day1 = new Intent(HomeActivity.this, DailyActivity.class);
                startActivity(day1);
                Toast.makeText(this, "Dayily page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_year:
                Intent day2 = new Intent(HomeActivity.this, activity_main_year.class);
                startActivity(day2);
                Toast.makeText(this, "Year plan page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_month:
                Intent day3 = new Intent(HomeActivity.this, activity_main_month.class);
                startActivity(day3);
                Toast.makeText(this, "Month plan page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_weekly:
                Intent day4 = new Intent(HomeActivity.this, activity_main_week.class);
                startActivity(day4);
                Toast.makeText(this, "Weekly plan page", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(logout);
                Toast.makeText(this, "Log out successfully", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}