package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.Model.StudentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/9658a5fcc6571c9bd7a1")
    Call<List<StudentModel>> getStudentList();

}
