package com.example.hao_activity_submission.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hao_activity_submission.BeerModelRoom;
import com.example.hao_activity_submission.Network.PunkRoomRepository;

import java.util.List;

public class BeerRoomViewModel extends AndroidViewModel {
    private PunkRoomRepository repository;
    public static LiveData<List<BeerModelRoom>> getAllCats;

    public BeerRoomViewModel(@NonNull Application application) {
        super(application);
        repository=new PunkRoomRepository(application);
        getAllCats=repository.getAllBeers();
    }

    public void insert(List<BeerModelRoom> cats){
        repository.insert(cats);
    }

    public LiveData<List<BeerModelRoom>> getAllCats()
    {
        return getAllCats;
    }

}
