package com.azcltd.android.test.usichenko.cities.view.ui;

import android.arch.lifecycle.Lifecycle;
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
import com.azcltd.android.test.usichenko.cities.service.models.Cities;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.adapters.CityAdapter;
import com.azcltd.android.test.usichenko.cities.view.callback.CityClickCallback;
import com.azcltd.android.test.usichenko.cities.viewmodel.CityListViewModel;

public class CityListFragment extends LifecycleFragment {

    private CityAdapter mCityAdapter;
    private FragmentCityListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_list, container, false);

        mCityAdapter = new CityAdapter(mCityClickCallback);
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
        viewModel.getCitiesObservable().observe(this, new Observer<Cities>() {
            @Override
            public void onChanged(@Nullable Cities cities) {
                binding.setIsLoading(false);
                if (cities != null) {
                    binding.setIsSuccess(true);
                    if (cities.getCities() != null && !cities.getCities().isEmpty()) {
                        binding.setIsEmpty(false);
                        mCityAdapter.setCityList(cities.getCities());
                    } else {
                        binding.setIsEmpty(true);
                    }
                } else {
                    binding.setIsSuccess(false);
                }
            }
        });
    }

    private final CityClickCallback mCityClickCallback = new CityClickCallback() {
        @Override
        public void onClick(City city) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showDetails(city);
            }
        }
    };
}
