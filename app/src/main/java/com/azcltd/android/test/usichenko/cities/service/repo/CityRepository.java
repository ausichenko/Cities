package com.azcltd.android.test.usichenko.cities.service.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.azcltd.android.test.usichenko.cities.service.models.Cities;
import com.azcltd.android.test.usichenko.cities.service.models.City;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityRepository {

    private AzoftService mAzoftService;
    private static CityRepository sCityRepository;

    private CityRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AzoftService.HTTP_AZOFT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAzoftService = retrofit.create(AzoftService.class);
    }

    public synchronized static CityRepository getInstance() {
        if (sCityRepository == null) {
            sCityRepository = new CityRepository();
        }

        return sCityRepository;
    }

    public LiveData<List<City>> getCityList() {
        final MutableLiveData<List<City>> data = new MutableLiveData<>();

        mAzoftService.getCityList().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(@NonNull Call<Cities> call, @NonNull Response<Cities> response) {
                data.setValue(response.body().getCities());
            }

            @Override
            public void onFailure(@NonNull Call<Cities> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
