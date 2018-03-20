package com.azcltd.android.test.usichenko.cities.service.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Cities {

    @Expose
    private List<City> cities = null;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
