package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.BeerModel;

import java.util.List;

public interface PunkCallback {
    void onSuccess(List<BeerModel> beers);

    void onFailure();


}
