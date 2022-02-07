package com.example.selfdisciplinemaster.Day1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal_day1 extends AndroidViewModel {

    private Day1Repository repository;

    private LiveData<List<Day1Modal>> allDay1s;

    public ViewModal_day1(@NonNull Application application) {
        super(application);
        repository = new Day1Repository(application);
        allDay1s = repository.getAllDay1s();
    }

    public void insert(Day1Modal model) {
        repository.insert(model);
    }

    public void update(Day1Modal model) {
        repository.update(model);
    }

    public void delete(Day1Modal model) {
        repository.delete(model);
    }

    public void deleteAllDay1s() {
        repository.deleteAllDay1s();
    }
    public LiveData<List<Day1Modal>> getAllDay1s() {
        return allDay1s;
    }
}
