package com.azcltd.android.test.usichenko.cities.service.repository;

import com.azcltd.android.test.usichenko.cities.service.models.Cities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AzoftService {

    String HTTP_AZOFT_URL = "http://azcltd.com/testTask/android/";

    @GET("cities.json")
    Call<Cities> getCityList();
}
