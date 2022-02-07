package com.example.selfdisciplinemaster.Day1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "day1_table")
public class Day1Modal {
    @PrimaryKey(autoGenerate = true)

    private int id;

    private String day1Title;

    private String day1TimeStart1;

    private String day1TimeStart2;

    private String day1TimeEnd;

    private String day1Date;

    private String day1Note;




    public Day1Modal(String day1Title, String day1TimeStart1, String day1TimeStart2, String day1TimeEnd, String day1Date, String day1Note) {
        this.day1Title = day1Title;
        this.day1TimeStart1 = day1TimeStart1;
        this.day1TimeStart2 = day1TimeStart2;
        this.day1TimeEnd = day1TimeEnd;
        this.day1Date = day1Date;
        this.day1Note = day1Note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay1Title() {
        return day1Title;
    }

    public void setDay1Title(String day1Title) {
        this.day1Title = day1Title;
    }

    public String getDay1TimeStart1() {
        return day1TimeStart1;
    }

    public void setDay1TimeStart1(String day1TimeStart1) {
        this.day1TimeStart1 = day1TimeStart1;
    }

    public String getDay1TimeStart2() {
        return day1TimeStart2;
    }

    public void setDay1TimeStart2(String day1TimeStart2) {
        this.day1TimeStart2 = day1TimeStart2;
    }

    public String getDay1TimeEnd() {
        return day1TimeEnd;
    }

    public void setDay1TimeEnd(String day1TimeEnd) {
        this.day1TimeEnd = day1TimeEnd;
    }

    public String getDay1Date() {
        return day1Date;
    }

    public void setDay1Date(String day1Date) {
        this.day1Date = day1Date;
    }

    public String getDay1Note() {
        return day1Note;
    }

    public void setDay1Note(String day1Note) {
        this.day1Note = day1Note;
    }
}
