package com.example.hao_activity_submission.Network;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.TabActivity;
import com.example.hao_activity_submission.Utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PunkRepository {
//    private Gson gson;
//    private PunkApi punkApi;
//    private SharedPreferences sharedPreferences;
    private Application application;
    private ArrayList<BeerModel> userArrayList = new ArrayList<>();
    private SingleLiveEvent<List<BeerModel>> mutableLiveData = new SingleLiveEvent<>();
    private int paging = 1;
//    public PunkRepository(PunkApi punkApi, SharedPreferences sharedPreferences, Gson gson) {
//        this.punkApi = punkApi;
//        this.sharedPreferences = sharedPreferences;
//        this.gson = gson;
//    }

    public PunkRepository(Application application) {
        this.application = application;
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



}
