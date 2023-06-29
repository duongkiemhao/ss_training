package com.example.hao_activity_submission;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Beer",indices = @Index(value = {"id"},unique = true))
public class BeerModelRoom {
    @PrimaryKey
    @NonNull
    private int id;
//    @ColumnInfo(name = "name")
    private String name;
//    @ColumnInfo(name = "tagline")
    private String tagline;
//    @ColumnInfo(name = "description")
    private String description;

    public static final int TYPE_ODD=0;
    public static final int TYPE_EVEN=1;


    @SerializedName("first_brewed")
    private String firstBrewed;

    @SerializedName("image_url")
    private String imageUrl;

//    @ColumnInfo(name = "abv")
    private double abv;
//    @ColumnInfo(name = "ibu")
    private double ibu;
//    @ColumnInfo(name = "ebc")
    private double ebc;
//    @ColumnInfo(name = "srm")
    private double srm;



    @SerializedName("brewers_tips")
    private String brewersTips;

    @SerializedName("contributed_by")
    private String contributedBy;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String tagline) {
        this.description = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }
    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public double getAbv() {
        return abv;
    }
    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }
    public void setIbu(double ibu) {
        this.ibu = ibu;
    }
    public double getEbc() {
        return ebc;
    }
    public void setEbc(double ebc) {
        this.ebc = ebc;
    }
    public double getSrm() {
        return srm;
    }
    public void setSrm(double srm) {
        this.srm = srm;
    }


    public String getBrewersTips() {
        return brewersTips;
    }
    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }
    public String getContributedBy() {
        return contributedBy;
    }
    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

}
