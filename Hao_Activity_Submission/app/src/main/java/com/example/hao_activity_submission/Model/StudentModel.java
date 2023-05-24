package com.example.hao_activity_submission.Model;

import com.google.gson.annotations.SerializedName;

public class StudentModel {
    @SerializedName("id")
    private String StudentId;
    @SerializedName("title")
    private String StudentName;


    public StudentModel(String studentId, String studentName) {
        this.StudentId = studentId;
        this.StudentName = studentName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setImage(String StudentName) {
        this.StudentName = StudentName;
    }

}
