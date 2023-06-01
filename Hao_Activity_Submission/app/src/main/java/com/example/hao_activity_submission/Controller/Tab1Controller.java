package com.example.hao_activity_submission.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.hao_activity_submission.Model.Util;
import com.example.hao_activity_submission.Model.BeerModel;
import com.example.hao_activity_submission.Network.PunkApi;
import com.example.hao_activity_submission.Network.PunkCallback;
import com.example.hao_activity_submission.Network.PunkRepository;
import com.example.hao_activity_submission.TabFragment.TabFragment1;
import com.google.gson.Gson;

import java.util.List;

public class Tab1Controller {
    private PunkRepository repository;
    private Gson gson;
    private int page = 1;
    private List<BeerModel> beers;
    private TabFragment1 view;

    public Tab1Controller(TabFragment1 tabFragment1, Gson gson, PunkRepository repository) {
        view = tabFragment1;
        this.repository = repository;
        this.gson = gson;
    }

    public void onStart() {
        page = 1;

        if (isConnectedToInternet()) {
            repository.getBeers(new PunkCallback() {
                @Override
                public void onSuccess(List<BeerModel> beers) {
                    view.showList(beers);
                    repository.saveList(beers);
                }

                @Override
                public void onFailure() {
                    Util.showToast(view.getActivity(), "API Error");
                }
            }, page);
        } else {
            view.showList(repository.loadDataFromCache());
        }
    }

    public void onStart2() {
        page++;

        if (isConnectedToInternet()) {
            repository.getBeers(new PunkCallback() {
                @Override
                public void onSuccess(List<BeerModel> beers) {
                    view.showList(beers);
                    repository.saveList(beers);
                }

                @Override
                public void onFailure() {
                    Util.showToast(view.getActivity(), "API Error");
                }
            }, page);
        } else {
            view.showList(repository.loadDataFromCache());
        }
    }

    public void onItemClick(BeerModel beer) {
        // view.navigateToDetails(beer);
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) view.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }



//    public void onBottomReached() {
//        if (page > PunkApi.MAXIMUM_PAGE) {
//            return;
//        }
//
//        page++;
//        if (isConnectedToInternet()) {
//            repository.getBeers(new PunkCallback() {
//                @Override
//                public void onSuccess(List<BeerModel> beers) {
//                    view.updateList(beers);
//                    repository.saveList(beers);
//                }
//
//                @Override
//                public void onFailure() {
//                    Util.showToast(view.getActivity(), "API Error");
//                }
//            }, page);
//        }
//    }



    public void searchByName(String beerName) {
        if (!isConnectedToInternet()) {
            Util.showToast(view.getActivity(), "Not connected to the internet");
        } else {
            repository.getBeerByName(new PunkCallback() {
                @Override
                public void onSuccess(List<BeerModel> beers) {
                    view.showSearchResults(beers);
                }

                @Override
                public void onFailure() {
                    Util.showToast(view.getActivity(), "API Error");
                }
            }, beerName);
        }
    }
}
