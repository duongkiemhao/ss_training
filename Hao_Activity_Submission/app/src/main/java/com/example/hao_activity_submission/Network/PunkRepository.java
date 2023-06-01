package com.example.hao_activity_submission.Network;

import android.content.SharedPreferences;

import com.example.hao_activity_submission.Constants;
import com.example.hao_activity_submission.Model.BeerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PunkRepository {
    private Gson gson;
    private PunkApi punkApi;
    private SharedPreferences sharedPreferences;

    public PunkRepository(PunkApi punkApi, SharedPreferences sharedPreferences, Gson gson) {
        this.punkApi = punkApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getBeers(final PunkCallback callback) {
        getBeers(callback);
    }

    public void getBeers(final PunkCallback callback, int page) {
        punkApi.getBeers(String.valueOf(page)).enqueue(new Callback<List<BeerModel>>() {
            @Override
            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void getBeerByName(final PunkCallback callback, String beerName) {
        punkApi.getBeerByName(beerName).enqueue(new Callback<List<BeerModel>>() {
            @Override
            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public List<BeerModel> loadDataFromCache() {
        if (!sharedPreferences.contains(Constants.PREFS_KEY_BEERS_LIST)) {
            System.out.println("Not connected to internet, no data in cache");
            return null;
        }

        String jsonPokemonList = sharedPreferences.getString(Constants.PREFS_KEY_BEERS_LIST, null);

        Type beersListType = new TypeToken<List<BeerModel>>() {
        }.getType();

        System.out.println("Not connected to internet, loading data from cache");
        return gson.fromJson(jsonPokemonList, beersListType);
    }

    public void saveList(List<BeerModel> beers) {

        String jsonBeersList = gson.toJson(beers);

        sharedPreferences.edit()
                .putString(Constants.PREFS_KEY_BEERS_LIST, jsonBeersList)
                .apply();
    }
}
