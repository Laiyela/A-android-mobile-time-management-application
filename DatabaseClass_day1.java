package com.example.selfdisciplinemaster.Day1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Day1Modal.class}, version = 1)
public abstract class DatabaseClass_day1 extends RoomDatabase {


    private static DatabaseClass_day1 instance;


    public abstract Dao_day1 Dao();


    public static synchronized DatabaseClass_day1 getInstance(Context context) {

        if (instance == null) {

            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseClass_day1.class, "day1_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }


    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(DatabaseClass_day1 instance) {
            Dao_day1 dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
