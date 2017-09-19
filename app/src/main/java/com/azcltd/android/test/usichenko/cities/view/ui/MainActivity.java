package com.azcltd.android.test.usichenko.cities.view.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.azcltd.android.test.usichenko.cities.R;

public class MainActivity extends AppCompatActivity {

    private CityListFragment mCityListFragment;
    private Fragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        changeFragment(mCityListFragment);
    }

    private void initFragments() {
        mCityListFragment = new CityListFragment();
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
