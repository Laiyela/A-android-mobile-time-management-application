package com.example.selfdisciplinemaster.Day1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.selfdisciplinemaster.Day2.MyDatabaseClass;

import java.util.List;

public class Day1Repository {

    private Dao_day1 dao;
    private LiveData<List<Day1Modal>> allDay1s;

    public Day1Repository(Application application) {
        MyDatabaseClass database = MyDatabaseClass.getInstance(application);
        dao = database.Dao1();
        allDay1s = dao.getAllDay1s();
    }

    public void insert(Day1Modal model) {
        new InsertDay1AsyncTask(dao).execute(model);
    }

    public void update(Day1Modal model) {
        new UpdateDay1AsyncTask(dao).execute(model);
    }

    public void delete(Day1Modal model) {
        new DeleteDay1AsyncTask(dao).execute(model);
    }

    public void deleteAllDay1s() {
        new DeleteAllDay1sAsyncTask(dao).execute();
    }

    public LiveData<List<Day1Modal>> getAllDay1s() {
        return allDay1s;
    }


    private static class InsertDay1AsyncTask extends AsyncTask<Day1Modal, Void, Void> {
        private Dao_day1 dao;

        private InsertDay1AsyncTask(Dao_day1 dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Day1Modal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateDay1AsyncTask extends AsyncTask<Day1Modal, Void, Void> {
        private Dao_day1 dao;

        private UpdateDay1AsyncTask(Dao_day1 dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Day1Modal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteDay1AsyncTask extends AsyncTask<Day1Modal, Void, Void> {
        private Dao_day1 dao;

        private DeleteDay1AsyncTask(Dao_day1 dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Day1Modal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllDay1sAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao_day1 dao;
        private DeleteAllDay1sAsyncTask(Dao_day1 dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllDay1s();
            return null;
        }
    }
}
