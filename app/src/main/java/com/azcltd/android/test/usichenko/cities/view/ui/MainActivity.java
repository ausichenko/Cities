package com.azcltd.android.test.usichenko.cities.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.service.models.City;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCityListFragment();
    }

    private void showCityListFragment() {
        CityListFragment cityListFragment = new CityListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, cityListFragment)
                .commit();
    }

    public void showDetails(City city) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(city);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
