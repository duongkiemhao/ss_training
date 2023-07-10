package com.example.hao_activity_submission.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hao_activity_submission.BeerModel;
import com.example.hao_activity_submission.BeerModelRoom;
import com.example.hao_activity_submission.Network.PunkRepository;
import com.example.hao_activity_submission.Network.PunkRoomRepository;
import com.example.hao_activity_submission.Utils.SingleLiveEvent;

import java.util.List;

public class BeerViewModel  extends AndroidViewModel {
    static PunkRepository repository;
    private PunkRoomRepository repositoryRoom;
    public static LiveData<List<BeerModelRoom>> getAllBeers;
//    private int page = 1;

    public BeerViewModel(@NonNull Application application) {
        super(application);
        repository = new PunkRepository(application);
        repositoryRoom=new PunkRoomRepository(application);
       // getAllBeers=repositoryRoom.getAllBeers();
    }

//    public static SingleLiveEvent<List<BeerModel>> getAllBeer(int page) {
//        return (SingleLiveEvent<List<BeerModel>>) repository.getBeers(page);
//    }

    public  MutableLiveData<List<BeerModel>> getAllBeer(int page) {
        return (MutableLiveData<List<BeerModel>>) repository.getBeers(page);
    }

    public void insert(List<BeerModelRoom> cats){
        repositoryRoom.insert(cats);
    }

    public LiveData<List<BeerModelRoom>> getAllCats()
    {
        return getAllBeers;
    }


    public LiveData<List<BeerModelRoom>> getAllCats(int a, int b)
    {
        getAllBeers=repositoryRoom.getAllBeers(a,b);
        return getAllBeers;
    }
}
