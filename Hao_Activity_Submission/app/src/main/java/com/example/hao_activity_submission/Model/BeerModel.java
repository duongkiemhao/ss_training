package com.example.hao_activity_submission.Model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Method;
import java.util.List;

public class BeerModel {
    private int id;
    private String name;
    private String tagline;
    private String description;

    @SerializedName("first_brewed")
    private String firstBrewed;

    @SerializedName("image_url")
    private String imageUrl;

    private double abv;
    private double ibu;
    private double ebc;
    private double srm;

    private QuantityModel volume;

    @SerializedName("boil_volume")
    private QuantityModel boilVolume;

    private Method method;

    private IngredientsModel ingredients;

    @SerializedName("food_pairing")
    private List<String> foodPairing;

    @SerializedName("brewers_tips")
    private String brewersTips;

    @SerializedName("contributed_by")
    private String contributedBy;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public double getIbu() {
        return ibu;
    }

    public double getEbc() {
        return ebc;
    }

    public double getSrm() {
        return srm;
    }

    public QuantityModel getVolume() {
        return volume;
    }

    public QuantityModel getBoilVolume() {
        return boilVolume;
    }

    public Method getMethod() {
        return method;
    }

    public IngredientsModel getIngredients() {
        return ingredients;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

}
