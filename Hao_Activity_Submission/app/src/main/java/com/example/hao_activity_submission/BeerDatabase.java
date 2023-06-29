package com.example.hao_activity_submission;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {BeerModelRoom.class},version = 1,exportSchema = false)
@TypeConverters(Converter.class)
public abstract class BeerDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "TheBeer";
    public abstract BeerModelDao beerModelDao();
    public static volatile BeerDatabase INSTANCE = null;

    public static BeerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BeerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, BeerDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE);
        }
    };

    static  class  PopulateDbAsync extends AsyncTask<Void,Void,Void> {
        private BeerModelDao beerModelDao;
        public PopulateDbAsync(BeerDatabase catDatabase)
        {
            beerModelDao=catDatabase.beerModelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            beerModelDao.deleteAll();
            return null;
        }
    }
}
