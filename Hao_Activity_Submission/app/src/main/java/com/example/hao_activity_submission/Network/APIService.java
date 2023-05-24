package com.example.hao_activity_submission.Network;

import com.example.hao_activity_submission.Model.StudentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/typicode/demo/posts")
    Call<List<StudentModel>> getStudentList();

}
