package com.example.selfdisciplinemaster.Week;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao_week {

    @Insert
    void insert(WeekModal model);

    @Update
    void update(WeekModal model);

    @Delete
    void delete(WeekModal model);

    @Query("DELETE FROM week_table")
    void deleteAllWeeks();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM week_table ORDER BY weekTitle ASC")
    LiveData<List<WeekModal>> getAllWeeks();
}
