package com.example.hao_activity_submission.Network;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hao_activity_submission.BeerDatabase;
import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.BeerModelDao;
import com.example.hao_activity_submission.BeerModelRoom;

import java.util.List;

public class PunkRoomRepository {
    public BeerModelDao beerModelDao;
    public LiveData<List<BeerModelRoom>> getAllBeers;
    private BeerDatabase database;

    public PunkRoomRepository(Application application){
        database=BeerDatabase.getInstance(application);
        beerModelDao=database.beerModelDao();
        getAllBeers=beerModelDao.gettingAllBeer();

    }

    public void insert(List<BeerModelRoom> cats){
        new InsertAsyncTask(beerModelDao).execute(cats);

    }

    public LiveData<List<BeerModelRoom>> getAllBeers(){
        return getAllBeers;
    }
    private static class InsertAsyncTask extends AsyncTask<List<BeerModelRoom>,Void,Void> {
        private BeerModelDao beerModelDao;

        public InsertAsyncTask(BeerModelDao beerModelDao2)
        {
            this.beerModelDao=beerModelDao2;
        }
        @Override
        protected Void doInBackground(List<BeerModelRoom>... lists) {
            beerModelDao.insert(lists[0]);
            return null;
        }
    }
}
