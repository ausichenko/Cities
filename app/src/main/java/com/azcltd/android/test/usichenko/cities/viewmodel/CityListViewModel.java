package com.azcltd.android.test.usichenko.cities.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.azcltd.android.test.usichenko.cities.service.models.Cities;
import com.azcltd.android.test.usichenko.cities.service.repo.CityRepository;

public class CityListViewModel extends AndroidViewModel {
    private final LiveData<Cities> mCityListObservable;

    public CityListViewModel(Application application) {
        super(application);

        mCityListObservable = CityRepository.getInstance().getCities();
    }

    public LiveData<Cities> getCitiesObservable() {
        return mCityListObservable;
    }
}
