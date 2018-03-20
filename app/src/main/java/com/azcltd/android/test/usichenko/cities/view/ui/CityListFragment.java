package com.azcltd.android.test.usichenko.cities.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azcltd.android.test.usichenko.cities.R;
import com.azcltd.android.test.usichenko.cities.databinding.FragmentCityListBinding;
import com.azcltd.android.test.usichenko.cities.service.models.Cities;
import com.azcltd.android.test.usichenko.cities.service.models.City;
import com.azcltd.android.test.usichenko.cities.view.adapters.CityAdapter;
import com.azcltd.android.test.usichenko.cities.view.callback.CityClickListener;
import com.azcltd.android.test.usichenko.cities.view.callback.TryAgainCallback;
import com.azcltd.android.test.usichenko.cities.viewmodel.CityListViewModel;

public class CityListFragment extends Fragment {

    private CityAdapter mCityAdapter;
    private FragmentCityListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_list, container, false);

        mCityAdapter = new CityAdapter(mCityClickCallback);
        mBinding.cityList.setAdapter(mCityAdapter);
        mBinding.setIsLoading(true);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CityListViewModel viewModel = ViewModelProviders.of(this).get(CityListViewModel.class);

        mBinding.setCallback(new TryAgainCallback() {
            @Override
            public void onClick() {
                reload(viewModel);
            }
        });

        observeViewModel(viewModel);
    }

    private void observeViewModel(CityListViewModel viewModel) {
        viewModel.getCitiesObservable().observe(this, new Observer<Cities>() {
            @Override
            public void onChanged(@Nullable Cities cities) {
                mBinding.setIsLoading(false);
                if (cities != null) {
                    mBinding.setIsSuccess(true);
                    if (cities.getCities() != null && !cities.getCities().isEmpty()) {
                        mBinding.setIsEmpty(false);
                        mCityAdapter.setCities(cities.getCities());
                    } else {
                        mBinding.setIsEmpty(true);
                    }
                } else {
                    mBinding.setIsSuccess(false);
                }
            }
        });
    }

    private void reload(CityListViewModel viewModel) {
        viewModel.getCitiesObservable().removeObservers(CityListFragment.this);
        viewModel.initObservable();
        mBinding.setIsLoading(true);
        observeViewModel(viewModel);
    }

    private final CityClickListener mCityClickCallback = new CityClickListener() {
        @Override
        public void onClick(City city) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showDetails(city);
            }
        }
    };
}
