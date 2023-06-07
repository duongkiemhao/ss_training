package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PunkApi {
    String BASE_URI = "https://api.punkapi.com/v2/";
    int RESULTS_PER_PAGE = 25;
    int MAXIMUM_PAGE = 5;


    @GET("beers?page=1")
    Call<List<BeerModel>> getBeers(@Query("per_page") String page);


}
