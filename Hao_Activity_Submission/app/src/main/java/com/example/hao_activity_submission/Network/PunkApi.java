package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.BeerModelRoom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PunkApi {
    String BASE_URI = "https://api.punkapi.com/v2/";
    int RESULTS_PER_PAGE = 25;
    int MAXIMUM_PAGE = 5;


    @GET("beers?per_page=10")
    Call<List<BeerModel>> getBeers(@Query("page") String page);

    @GET("beers?per_page=10")
    Call<List<BeerModelRoom>> getBeers2(@Query("page") String page);
}
