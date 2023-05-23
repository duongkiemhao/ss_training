package com.example.hao_activity_submission.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hao_activity_submission.Model.StudentModel;
import com.example.hao_activity_submission.Network.APIService;
import com.example.hao_activity_submission.Network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListViewModel extends ViewModel {
    private MutableLiveData<List<StudentModel>> studentList;

    public StudentListViewModel(){
        studentList = new MutableLiveData<>();
    }

    public MutableLiveData<List<StudentModel>> getStudentListObserver() {
        return studentList;

    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<StudentModel>> call = apiService.getStudentList();
        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                studentList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                studentList.postValue(null);
            }
        });
    }
}
