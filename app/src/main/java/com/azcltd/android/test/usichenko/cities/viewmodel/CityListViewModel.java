package com.azcltd.android.test.usichenko.cities.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.azcltd.android.test.usichenko.cities.service.models.Cities;
import com.azcltd.android.test.usichenko.cities.service.repository.CityRepository;

public class CityListViewModel extends AndroidViewModel {
    private LiveData<Cities> mCityListObservable;

    public CityListViewModel(Application application) {
        super(application);

        initObservable();
    }

    public LiveData<Cities> getCitiesObservable() {
        return mCityListObservable;
    }

    public void initObservable() {
        mCityListObservable = CityRepository.getInstance().getCities();
    }
}
