package com.example.selfdisciplinemaster.Day1;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao_day1 {

    @Insert
    void insert(Day1Modal model);


    @Update
    void update(Day1Modal model);


    @Delete
    void delete(Day1Modal model);


    @Query("DELETE FROM day1_table")
    void deleteAllDay1s();


    @Query("SELECT * FROM day1_table ORDER BY day1Title ASC")
    LiveData<List<Day1Modal>> getAllDay1s();
}
