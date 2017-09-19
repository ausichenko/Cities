package com.azcltd.android.test.usichenko.cities.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.service.repo.CityRepository;

import java.util.List;

public class CityListViewModel extends AndroidViewModel {
    private final LiveData<List<City>> mCityListObservable;

    public CityListViewModel(Application application) {
        super(application);

        mCityListObservable = CityRepository.getInstance().getCityList();
    }

    public LiveData<List<City>> getCityListObservable() {
        return mCityListObservable;
    }
}
