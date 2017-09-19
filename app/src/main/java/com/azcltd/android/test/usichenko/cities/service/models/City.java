package com.azcltd.android.test.usichenko.cities.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("location")
    @Expose
    public Location location;

    public City() {

    }
}
