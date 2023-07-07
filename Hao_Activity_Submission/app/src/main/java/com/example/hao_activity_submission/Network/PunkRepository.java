package com.example.hao_activity_submission.Network;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hao_activity_submission.BeerDatabase;
import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.BeerModelDao;
import com.example.hao_activity_submission.BeerModelRoom;
import com.example.hao_activity_submission.TabActivity;
import com.example.hao_activity_submission.Utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PunkRepository {

    //Tab 1
    private Application application;
    private ArrayList<BeerModel> userArrayList = new ArrayList<>();
    private SingleLiveEvent<List<BeerModel>> mutableLiveData = new SingleLiveEvent<>();
    private int paging = 1;
    //Insert to Room
    public BeerModelDao beerModelDao;
    public LiveData<List<BeerModelRoom>> getAllBeers;
    private BeerDatabase database;

    public PunkRepository(Application application) {
        this.application = application;
        database=BeerDatabase.getInstance(application);
        beerModelDao=database.beerModelDao();
       // getAllBeers=beerModelDao.gettingAllBeer();
    }




    public SingleLiveEvent<List<BeerModel>> getBeers( int page) {
        PunkApi apiService = RetroInstance.getRetrofitInstance();
        Call<List<BeerModel>> call = apiService.getBeers(String.valueOf(page));
        call.enqueue(new Callback<List<BeerModel>>() {
            @Override
            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
                if (response.body() != null) {
                    userArrayList = (ArrayList<BeerModel>) response.body();
                    mutableLiveData.setValue(userArrayList);

                }
            }
            @Override
            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }



    public void getBeers(final PunkCallback callback) {
        getBeers(callback);
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

    public void makeRequest(int t) {
        PunkApi apiService = RetroInstance.getRetrofitInstance();
        Call<List<BeerModelRoom>> call = apiService.getBeers2(String.valueOf(t));
        call.enqueue(new retrofit2.Callback<List<BeerModelRoom>>() {
            @Override
            public void onResponse(Call<List<BeerModelRoom>> call, Response<List<BeerModelRoom>> response) {
                if (response.body() != null) {
//
                    insert(response.body());
                    response.body();

                }
            }

            @Override
            public void onFailure(Call<List<BeerModelRoom>> call, Throwable t) {
                Log.d("main", "onFailure: " + t.getMessage());
            }
        });
    }

}
