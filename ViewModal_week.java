package com.example.selfdisciplinemaster.Week;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal_week extends AndroidViewModel {

    // creating a new variable for course repository.
    private WeekRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<WeekModal>> allWeeks;

    // constructor for our view modal.
    public ViewModal_week(@NonNull Application application) {
        super(application);
        repository = new WeekRepository(application);
        allWeeks = repository.getAllWeeks();
    }

    // below method is use to insert the data to our repository.
    public void insert(WeekModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(WeekModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(WeekModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllWeeks() {
        repository.deleteAllWeeks();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<WeekModal>> getAllWeeks() {
        return allWeeks;
    }
}
