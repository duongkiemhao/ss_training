package com.example.hao_activity_submission.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Method {
    @SerializedName("mash_temp")
    private List<MashTemp> mashTemp;

    private Fermentation fermentation;

    private String twist;

    public MashTemp getMashTemp() {
        return mashTemp.get(0);
    }

    public Fermentation getFermentation() {
        return fermentation;
    }

    public String getTwist() {
        return twist;
    }

    public class MashTemp {

        @SerializedName("temp")
        private QuantityModel temperature;
        private double duration;

        public QuantityModel getTemperature() {
            return temperature;
        }

        public double getDuration() {
            return duration;
        }
    }

    public class Fermentation {
        @SerializedName("temp")
        private QuantityModel temperature;

        public QuantityModel getTemperature() {
            return temperature;
        }
    }
}
