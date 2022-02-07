package com.example.selfdisciplinemaster.Week;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "week_table")
public class WeekModal {

    @PrimaryKey(autoGenerate = true)

    private int id;

    private String weekTitle;

    private String weekDescription;

    private String weekOne;

    public WeekModal(String weekTitle, String weekDescription, String weekOne) {
        this.weekTitle = weekTitle;
        this.weekDescription = weekDescription;
        this.weekOne = weekOne;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }

    public String getWeekDescription() {
        return weekDescription;
    }

    public void setWeekDescription(String weekDescription) {
        this.weekDescription = weekDescription;
    }

    public String getWeekOne() {
        return weekOne;
    }

    public void setWeekOne(String weekOne) {
        this.weekOne = weekOne;
    }


}
