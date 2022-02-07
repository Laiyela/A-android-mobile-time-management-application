package com.example.selfdisciplinemaster.Week;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WeekRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private Dao_week dao;
    private LiveData<List<WeekModal>> allWeeks;

    // creating a constructor for our variables
    // and passing the variables to it.
    public WeekRepository(Application application) {
        DatabaseClass_week database = DatabaseClass_week.getInstance(application);
        dao = database.Dao();
        allWeeks = dao.getAllWeeks();
    }

    // creating a method to insert the data to our database.
    public void insert(WeekModal model) {
        new InsertWeekAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(WeekModal model) {
        new UpdateWeekAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(WeekModal model) {
        new DeleteWeekAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllWeeks() {
        new DeleteAllWeeksAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<WeekModal>> getAllWeeks() {
        return allWeeks;
    }


    // we are creating a async task method to insert new course.
    private static class InsertWeekAsyncTask extends AsyncTask<WeekModal, Void, Void> {
        private Dao_week dao;

        private InsertWeekAsyncTask(Dao_week dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WeekModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateWeekAsyncTask extends AsyncTask<WeekModal, Void, Void> {
        private Dao_week dao;

        private UpdateWeekAsyncTask(Dao_week dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WeekModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteWeekAsyncTask extends AsyncTask<WeekModal, Void, Void> {
        private Dao_week dao;

        private DeleteWeekAsyncTask(Dao_week dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WeekModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllWeeksAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao_week dao;
        private DeleteAllWeeksAsyncTask(Dao_week dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllWeeks();
            return null;
        }
    }
}
