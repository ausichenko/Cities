package com.azcltd.android.test.usichenko.cities.service.repo;

import com.azcltd.android.test.usichenko.cities.service.models.Cities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AzoftService {

    String HTTP_AZOFT_URL = "http://azcltd.com/";

    @GET("testTask/android/cities.json")
    Call<Cities> getCityList();
}
