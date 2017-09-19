package com.azcltd.android.test.usichenko.cities.view.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.FragmentCityListBinding;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.adapters.CityAdapter;
import com.azcltd.android.test.usichenko.cities.viewmodel.CityListViewModel;

import java.util.List;

public class CityListFragment extends LifecycleFragment {

    private CityAdapter mCityAdapter;
    private FragmentCityListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_list, container, false);

        mCityAdapter = new CityAdapter();//projectClickCallback);
        binding.cityList.setAdapter(mCityAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CityListViewModel viewModel = ViewModelProviders.of(this).get(CityListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(CityListViewModel viewModel) {
        viewModel.getCityListObservable().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                if (cities != null) {
                    binding.setIsLoading(false);
                    mCityAdapter.setCityList(cities);
                }
            }
        });
    }
}
