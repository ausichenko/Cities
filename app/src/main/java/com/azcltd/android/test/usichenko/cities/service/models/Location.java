package com.azcltd.android.test.usichenko.cities.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude")
    @Expose
    public double latitude;

    @SerializedName("longitude")
    @Expose
    public double longitude;

    public Location() {

    }
}
