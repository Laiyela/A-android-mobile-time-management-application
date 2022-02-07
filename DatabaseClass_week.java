package com.example.selfdisciplinemaster.Week;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.selfdisciplinemaster.Month.Dao_month;
import com.example.selfdisciplinemaster.Month.MonthModal;
import com.example.selfdisciplinemaster.Year.Dao_year;
import com.example.selfdisciplinemaster.Year.YearModal;



    @Database(entities = {WeekModal.class, MonthModal.class, YearModal.class}, version = 4)
public abstract class DatabaseClass_week extends RoomDatabase {

    private static DatabaseClass_week instance;

    public abstract Dao_week Dao();
    public abstract Dao_month DaoMonth();
    public abstract Dao_year DaoYear();

    public static synchronized DatabaseClass_week getInstance(Context context) {

        if (instance == null) {

            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseClass_week.class, "week_database")

                            .fallbackToDestructiveMigration()

                            .addCallback(roomCallback)

                            .build();
        }
        return instance;
    }

    // below line is to create a callback for our room database.
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(DatabaseClass_week instance) {
            Dao_week dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
