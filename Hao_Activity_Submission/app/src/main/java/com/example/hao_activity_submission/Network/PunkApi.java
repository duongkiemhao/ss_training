package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.Model.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PunkApi {
    String BASE_URI = "https://api.punkapi.com/v2/";
    int RESULTS_PER_PAGE = 25;
    int MAXIMUM_PAGE = 5;

    @GET("beers/")
    Call<List<BeerModel>> getBeers();

    @GET("beers?per_page=5")
    Call<List<BeerModel>> getBeers(@Query("page") String page);

    @GET("beers/")
    Call<List<BeerModel>> getBeerById(@Query("id") int id);

    @GET("beers/")
    Call<List<BeerModel>> getBeerByName(@Query("beer_name") String beerName);

    @GET("beers/random")
    Call<List<BeerModel>> getRandomBeer();
}
