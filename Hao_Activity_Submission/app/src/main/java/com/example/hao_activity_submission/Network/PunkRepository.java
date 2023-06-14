package com.example.hao_activity_submission.Network;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.TabActivity;

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
    private MutableLiveData<List<BeerModel>> mutableLiveData = new MutableLiveData<>();
    private int paging = 1;
//    public PunkRepository(PunkApi punkApi, SharedPreferences sharedPreferences, Gson gson) {
//        this.punkApi = punkApi;
//        this.sharedPreferences = sharedPreferences;
//        this.gson = gson;
//    }

    public PunkRepository(Application application) {
        this.application = application;
    }




    public MutableLiveData<List<BeerModel>> getBeers( int page) {
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
//
//    public void getBeers(final PunkCallback callback, int page) {
//        punkApi.getBeers(String.valueOf(page)).enqueue(new Callback<List<BeerModel>>() {
//            @Override
//            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.onSuccess(response.body());
//                } else {
//                    callback.onFailure();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
//                callback.onFailure();
//            }
//        });
//    }


}
