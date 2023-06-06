package com.example.hao_activity_submission.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.Network.PunkRepository;

import java.util.List;

public class BeerViewModel  extends AndroidViewModel {
    static PunkRepository repository;
//    private int page = 1;

    public BeerViewModel(@NonNull Application application) {
        super(application);
        repository = new PunkRepository(application);
    }

    public static MutableLiveData<List<BeerModel>> getAllBeer(int page) {
        return repository.getBeers(page);
    }
}
