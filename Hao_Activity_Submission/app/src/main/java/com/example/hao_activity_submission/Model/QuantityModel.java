package com.example.hao_activity_submission.Model;

import androidx.annotation.NonNull;

public class QuantityModel {
    private double value;
    private String unit;

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @NonNull
    @Override
    public String toString() {
        return value + " " + unit;
    }
}
